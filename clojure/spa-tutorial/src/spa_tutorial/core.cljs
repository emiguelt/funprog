(ns spa-tutorial.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [secretary.core :as sec :include-macros true]
            [goog.events :as events]
            [goog.history.EventType :as EventType])
  (:import goog.History))

(enable-console-print!)

(sec/set-config! :prefix "#")

;;setup history API

(let [history (History.)
      navigation EventType/NAVIGATE]
  (goog.events/listen history
                      navigation
                      #(-> % .-token sec/dispatch!))
  (doto history (.setEnabled true)))

;;components
;;navigation-view will be shared by our three main components

(defn navigation-view [app owner]
  (reify
    om/IRender
    (render
      [_]
      (let [style {:style {:margin "10px;"}}]
        (dom/div style
                 (dom/a (assoc style :href "#/")
                        "Home")
                 (dom/a (assoc style :href "#/contact")
                        "Contact")
                 (dom/a (assoc style :href "#/about")
                        "About"))))))

;;home page component
(defn index-page-view [app owner]
  (reify
    om/IRender
    (render
      [_]
      (dom/div nil
        (om/build navigation-view {})
        (dom/h1 "Index page")))))

;;contact page component
(defn contact-page-view [app owner]
  (reify
    om/IRender
      (render
        [_]
        (dom/div nil
          (om/build navigation-view {})
          (dom/h1 "Contact page")))))

;;about page component
(defn about-page-view [app owner]
  (reify
    om/IRender
      (render
        [_]
        (dom/div nil
          (om/build navigation-view {})
          (dom/h1 "About page")))))

;;setup secretary routes
(sec/defroute index-page "/" []
  (om/root index-page-view
           {}
           {:target (. js/document (getElementById "app"))}))

(sec/defroute contact-page "/contact" []
  (om/root contact-page-view
           {}
           {:target (. js/document (getElementById "app"))}))
(sec/defroute about-page "/about" []
  (om/root about-page-view
           {}
           {:target (. js/document (getElementById "app"))}))

;;initialization
(defn main []
  (-> js/document
      .-location
      (set! "#/")))

(sec/dispatch! "/")


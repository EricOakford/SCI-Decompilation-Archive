;;; Sierra Script 1.0 - (do not remove this comment)
(script# EVENT_HANDLER)
(include game.sh)
(use Set)


(class EventHandler kindof Set
	;;; An EventHandler is a Set that passes events to its elements.

;;;	(methods
;;;		handleEvent
;;;	)

	(method (handleEvent event &tmp node evtClone ret obj)
		(= evtClone (Clone event))
		(for	((= node (KList LFirstNode elements)))
				(and node (not (evtClone claimed?)))
				((= node nextNode))
			(= nextNode (KList LNextNode node))
			(= obj (KList LNodeValue node))
			(obj handleEvent: evtClone)
		)
		(= ret (evtClone claimed?))
		(evtClone dispose:)
		(return ret)
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# STOPWALK)
(include game.sh)
(use Motion)

(public
	StopWalk 0
)

(class StopWalk kindof Forward
	;;; Do a forward cycle of an object only if it has moved.  Otherwise,
	;;;	remain motionless.  Questions/suggestions to Corey Cole.
	;;;
	;;; Usage example:
	;;;	(actor setCycle: StopWalk stoppedView)
	;;;
	;;; (walking view will be actor's view at StopWalk init time).

	(properties
		vWalking	0		; Normal view for actor (walking view).
		vStopped	0		; View to use when stopped.
	)

	(method (init who stopView)
		(if argc					(= vWalking ((= client who) view?))
			(if (>= argc 2)	(= vStopped stopView)
			)
		)
		(super init: client)
	)

	(method (dispose)
		(if (== (client view?) vStopped)
			(client  view: vWalking)			; Leave on normal view
		)
		(super dispose:)
	)

	(method (doit  &tmp st mv)
		;; Goes to next cel if client has moved.
		
		(if (client isStopped:)
			(if (== (client  view?) vWalking)
				(client  view: vStopped)
				(= mv (client mover?))
				(if (and mv (not (mv completed?)))
					(client  setMotion: 0)	; Blocked, so stop trying to move
				)
				(super doit:)
			)
		else
			(if (== (client  view?) vStopped)
				(client  view: vWalking)
			)
			(super doit:)
		)
	)
)
;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	STOPWALK.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Corey Cole
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	Changes an actor's view, loop, and/or cel when he stops moving
;;;;
;;;;	Usage example:
;;;;		(actor setCycle: StopWalk stoppedView)
;;;;		(actor setCycle: StopWalk SAMEVIEW)
;;;;
;;;;	The walking view will be the actor's current view at the time
;;;;	the StopWalk was inited.  SAMEVIEW indicates that the stopped
;;;;	cels are in the last loop of the walking view.
;;;;
;;;;	Classes:
;;;;		StopWalk


(script# STOPWALK)
(include game.sh)
(use Motion)

(public
	StopWalk	0
)


;(define	LastLoop	(- (NumLoops client) 1))


(class StopWalk kindof Forward
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
		(self doit:)
	)

	(method (dispose)
		(if (== (client view?) vStopped)
			(client  view: vWalking)			; Leave on normal view
		)
		(super dispose:)
	)

	(method (doit &tmp curLoop theMover)
		(if (client isStopped:)
			(cond

				; Single view - we're stopped but haven't changed yet
				((and	(== vStopped -1)
						(!= (client loop?) (- (NumLoops client) 1))
					)
					(= curLoop (client loop?))
					(super doit:)
					(client
						loop: 	(- (NumLoops client) 1),
						setCel:	curLoop
					)
				)

				; Dual view - we're stopped but haven't changed yet
				((and	(!= vStopped -1)
						(== (client view?) vWalking)
					)
					(client view: vStopped)
					(= clientLastCel (client lastCel:) )
					(super doit:)
				)

				; Dual view - we're stopped, but we want to continue cycling
				((!= vStopped -1)
					(super doit:)
				)
			)
		else
			; We're not stopped
			(switch vStopped
				((client view?)		; dual view
					(client view: vWalking)
				)
				(-1						; same view
					(client setLoop: -1)
					(if (== (client loop?) (- (NumLoops client) 1))
						(client
							loop:		(client cel?),
							cel:		0
						)
					)
				)
			)
			(= clientLastCel (client lastCel:) )
			(super doit:)
		)
	)
)
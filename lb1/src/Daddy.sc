;;; Sierra Script 1.0 - (do not remove this comment)
(script# 412)
(include game.sh)
(use Main)
(use Motion)
(use Actor)
(use System)

(public
	Daddy 0
)

(instance Daddy of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(cast contains: daddyBow)
				(== (daddyBow cel?) (- (NumCels daddyBow) 2))
			)
			(daddyBow posn: 140 105 setCel: (daddyBow lastCel:))
		)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 412)
	)
	
	(method (changeState newState &tmp egoX egoY)
		(switch (= state newState)
			(0
				(= saveDisabled TRUE)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(daddyBow
					setLoop: 2
					posn: (+ egoX 6) (- egoY 47)
					setPri: 15
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 140 105 self
					init:
				)
				(ego stopUpd:)
			)
			(1
				(= cycles 13)
			)
			(2
				(daddyBow hide: dispose: delete:)
				(= cycles 2)
			)
			(3
				(if (not (Btst fDaddyTip1))
					(= theTalker talkDADDY)
					(Say 1 412 0)
					(Bset fDaddyTip1)
				)
				(= cycles 1)
			)
			(4
				(if (not (Btst fDaddyTip2))
					(= theTalker 26)
					(Say 1 412 1)
					(Bset fDaddyTip2)
				)
				(= cycles 1)
			)
			(5
				(if (not (Btst fDaddyTip3))
					(= theTalker talkDADDY)
					(Say 1 412 2)
					(Bset fDaddyTip3)
				)
				(= cycles 1)
			)
			(6
				(= theTalker talkLAURA)
				(Say 0 412 3)
				(ego startUpd:)
				(= saveDisabled FALSE)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance daddyBow of Actor
	(properties
		view 925
		signal ignrAct
		illegalBits $0000
	)
)

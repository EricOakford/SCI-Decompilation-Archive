;;; Sierra Script 1.0 - (do not remove this comment)
(script# 485)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm485 0
)

(instance rm485 of Room
	(properties
		picture 485
	)
	
	(method (init)
		(HandsOff)
		((ScriptID JOHARI_TALKER 1)
			view: 485
			x: 105
			y: 129
			loop: 0
			cel: 0
			init:
			setScript: transform
		)
		(super init:)
	)
	
	(method (dispose)
		(DisposeScript JOHARI_TALKER)
		(super dispose:)
	)
	
	(method (cue)
		(if (== (cSound prevSignal?) -1)
			(curRoom newRoom: 480)
		)
	)
)

(instance transform of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound setLoop: 1 changeTo: 622 self)
				(= seconds 2)
			)
			(1
				((ScriptID 36 1) setCycle: Forward)
				(= seconds 3)
			)
			(2
				(if (or (== (Random 1 50) 25) (Btst fPrisonerEasterEgg))
					(egoHead setCycle: EndLoop init:)
					(globalSound number: 401 setLoop: 1 play: 127)
				)
				((ScriptID JOHARI_TALKER 1) setCycle: EndLoop)
				(= seconds 5)
			)
			(3)
			(4
				(curRoom newRoom: 480)
			)
		)
	)
)

(instance egoHead of Prop
	(properties
		x 210
		y 78
		view 486
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 84)
(include game.sh)
(use Main)
(use KQ5Room)
(use Sync)
(use Motion)
(use Actor)
(use System)

(public
	rm084 0
)

(local
	local0
)
(instance rm084 of KQ5Room
	(properties
		picture 84
	)
	
	(method (init)
		(super init:)
		(Load VIEW 796)
		(Load SCRIPT 941)
		(theMouth init:)
		(arms init:)
		(theEyes init:)
		(self setScript: cartoon)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(curRoom newRoom: temp0)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(super dispose:)
	)
)

(instance cartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(theMouth setCycle: MouthSync 5099)
				(SpeakAudio 5099 self)
			)
			(2
				(cls)
				(theMouth setCycle: 0)
				(= seconds 2)
			)
			(3
				(arms cycleSpeed: 1 setCycle: EndLoop)
				(= local0 1)
				(= seconds 2)
			)
			(4 (curRoom newRoom: 83))
		)
	)
)

(instance theMouth of Prop
	(properties
		x 105
		y 72
		view 1032
		cel 4
		priority 10
		signal $0810
		cycleSpeed 2
	)
)

(instance theEyes of Prop
	(properties
		x 119
		y 60
		view 780
		loop 1
		priority 10
		signal $0810
	)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== (Random 1 40) 1) (not cycler) (not local0))
			(self setCycle: EndLoop)
		)
	)
)

(instance arms of Prop
	(properties
		x 128
		y 93
		view 780
		loop 2
		priority 10
		signal $0810
	)
)

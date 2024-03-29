;;; Sierra Script 1.0 - (do not remove this comment)
(script# 672)
(include game.sh)
(use Main)
(use AudioScript)
(use KQ5Room)
(use Motion)
(use Actor)
(use System)

(public
	cdEnding3 0
)

(instance cdEnding3 of KQ5Room
	(properties
		picture 218
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(theGame setCursor: invCursor TRUE)
		(Load SCRIPT 941)
		(Load SCRIPT 929)
		(Load VIEW 936)
		(Load RES_SYNC 10124)
		(theMouth init:)
		(arm init:)
		(casEyes init:)
		(alBlinking init:)
		(valBlinking init:)
		(rosEyes init:)
		(self setScript: cartoon)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(super dispose:)
	)
)

(instance cartoon of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(theMouth play: 10124 670)
				(= waitForCue 17152)
			)
			(2
				(cls)
				(if (== (theGame detailLevel:) 3)
					(arm setScript: armScript)
				)
				(= waitForCue 17408)
			)
			(3
				(cls)
				(theMouth changeMouth: 0)
				(= cycles 1)
			)
			(4
				(if (> (DoAudio Loc) -1) (-- state))
				(= cycles 1)
			)
			(5
				(cls)
				(casEyes hide:)
				(alBlinking hide:)
				(valBlinking hide:)
				(rosEyes hide:)
				(alTalking setCycle: 0 setCel: 0)
				(curRoom newRoom: 670)
			)
		)
	)
)

(instance egoMouth of Prop
	(properties
		x 116
		y 34
		view 936
		priority 1
		cycleSpeed 2
	)
)

(instance arm of Prop
	(properties
		x 90
		y 40
		view 936
		loop 1
		priority 1
		cycleSpeed 2
	)
)

(instance casEyes of Prop
	(properties
		x 64
		y 28
		view 936
		loop 2
		priority 1
		cycleSpeed 2
	)
	
	(method (doit)
		(super doit:)
		(switch (Random 1 40)
			(1
				(if (and (not script) (== (theGame detailLevel:) 3))
					(self setScript: (bodyScript new:))
				)
			)
		)
	)
)

(instance alTalking of Prop
	(properties
		x 270
		y 32
		view 936
		loop 3
		priority 1
		cycleSpeed 2
	)
)

(instance alBlinking of Prop
	(properties
		x 260
		y 18
		view 936
		loop 4
		priority 1
		cycleSpeed 2
	)
	
	(method (doit)
		(super doit:)
		(switch (Random 1 40)
			(1
				(if (not script) (self setScript: (bodyScript new:)))
			)
		)
	)
)

(instance valBlinking of Prop
	(properties
		x 144
		y 32
		view 936
		loop 5
		priority 1
		cycleSpeed 2
	)
	
	(method (doit)
		(super doit:)
		(switch (Random 1 40)
			(1
				(if (not script) (self setScript: (bodyScript new:)))
			)
		)
	)
)

(instance rosEyes of Prop
	(properties
		x 202
		y 37
		view 936
		loop 6
		priority 1
		cycleSpeed 2
	)
	
	(method (doit)
		(super doit:)
		(switch (Random 1 40)
			(1
				(if (not script) (self setScript: (bodyScript new:)))
			)
		)
	)
)

(instance theMouth of MonoAudioProp
	(properties
		x 116
		y 34
		view 936
		priority 1
		cycleSpeed 2
	)
	
	(method (changeMouth param1)
		(switch param1
			(0
				(theMouth
					view: 936
					loop: 3
					cycleSpeed: 2
					priority: 1
					x: 270
					y: 32
				)
			)
		)
	)
)

(instance armScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1 (= seconds 6))
			(2 (client setCycle: BegLoop self))
			(3 (client setScript: 0))
		)
	)
)

(instance bodyScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1 (client setCycle: BegLoop self))
			(2 (client setScript: 0))
		)
	)
)

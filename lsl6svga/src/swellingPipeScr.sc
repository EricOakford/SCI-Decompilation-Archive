;;; Sierra Script 1.0 - (do not remove this comment)
(script# 332)
(include sci.sh)
(use Main)
(use EgoDead)
(use rm330)
(use PolyPath)
(use Sound)
(use Motion)
(use System)

(public
	swellingPipeScr 0
	pistonExplosionScr 1
)

(instance swellingPipeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 330 3)
					init:
					cycleSpeed: 15
					setCycle: End self
				)
			)
			(1
				(theGame handsOff:)
				(if (curRoom script?)
					(curRoom setScript: 0)
					(ego setMotion: 0)
				)
				(ego setHeading: 310 self)
			)
			(2 (messager say: 2 4 6 1 self))
			(3
				(self setScript: egoToPipeScr self)
				(= cycles 2)
			)
			(4 (messager say: 2 4 6 2 self))
			(5
				((ScriptID 330 3) loop: 1 cel: 0 setCycle: Fwd)
				(= register 15)
				(= cycles 2)
			)
			(6
				(if (-- register)
					((ScriptID 330 3) cycleSpeed: register)
					(= ticks 20)
					(-- state)
				else
					(= cycles 2)
				)
			)
			(7 (script cue:))
			(8
				((ScriptID 330 3) setPri: 1)
				(self setScript: tankExplosionScr self)
			)
			(9 (= cycles 2))
			(10 (EgoDead 10 self))
			(11
				(proc330_6)
				(theGame handsOn:)
				(ego
					view: 900
					setLoop: -1
					posn: ((ScriptID 330 7) approachX?) ((ScriptID 330 7) approachY?)
					loop: 8
					cel: 6
				)
				(self dispose:)
			)
		)
	)
)

(instance tankExplosionScr of Script
	(properties)
	
	(method (dispose)
		(sounds delete: (explosionFx dispose: yourself:))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 330 3) loop: 0 cel: 4 setCycle: Beg)
				(explosionFx play:)
				((ScriptID 330 5)
					init:
					loop: 1
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(1
				(ego view: 903 setLoop: 3 1)
				((ScriptID 330 5) setCycle: End self)
			)
			(2
				((ScriptID 330 5) loop: 2 cel: 0 setCycle: End self)
			)
			(3 (self dispose:))
		)
	)
)

(instance pistonExplosionScr of Script
	(properties)
	
	(method (dispose)
		(sounds delete: (explosionFx dispose: yourself:))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (curRoom script?) (curRoom setScript: 0))
				(= ticks 10)
			)
			(1 (ego setHeading: 310 self))
			(2 (= cycles 2))
			(3
				(messager say: 2 4 1 0 self)
				(= ticks 90)
			)
			(4 (ego setHeading: 270))
			(5
				(ego setMotion: PolyPath 100 130 self)
			)
			(6
				(ego setPri: 87 setHeading: 315 self)
			)
			(7 (= cycles 2))
			(8
				(explosionFx play:)
				((ScriptID 330 4) loop: 1 cel: 0 setCycle: CT 3 1 self)
				((ScriptID 330 8) hide:)
			)
			(9
				(ego setPri: -1 view: 903 setLoop: 3)
				((ScriptID 330 4) setCycle: End self)
			)
			(10
				((ScriptID 330 4) loop: 2 cel: 0 setCycle: End self)
			)
			(11 (self dispose:))
		)
	)
)

(instance explosionFx of Sound
	(properties
		flags $0001
		number 800
	)
)

(instance egoToPipeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 151 127 self)
			)
			(1 (ego setHeading: 315 self))
			(2)
			(3 (self dispose:))
		)
	)
)

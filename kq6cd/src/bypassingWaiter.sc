;;; Sierra Script 1.0 - (do not remove this comment)
(script# 731)
(include sci.sh)
(use Main)
(use rm730)
(use Kq6Procs)
(use Motion)
(use Actor)
(use System)

(public
	bypassingWaiter 0
)

(instance bypassingWaiter of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 731)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 59)
				(= cycles 2)
			)
			(1 (messager say: 1 0 1 1 self))
			(2
				(self setScript: convScript self)
			)
			(3 (messager say: 1 0 1 2 self))
			(4
				(self setScript: convScript self)
			)
			(5 (messager say: 1 0 1 3 self))
			(6
				(self setScript: convScript self)
			)
			(7 (messager say: 1 0 1 4 self))
			(8
				(self setScript: convScript self)
			)
			(9 (= cycles 2))
			(10
				(theGame handsOn:)
				(Bclr 59)
				(self dispose:)
			)
		)
	)
)

(instance convScript of Script
	(properties)
	
	(method (dispose)
		(= start (+ state 1))
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setHeading: 315)
				(soundFx2 number: 901 loop: 1 play:)
				(soundFx number: 731 loop: -1 play:)
				((ScriptID 730 1) setCycle: EndLoop self)
			)
			(1
				(soundFx2 stop:)
				(self dispose:)
			)
			(2
				(waiter
					init:
					ignoreActors:
					illegalBits: 0
					cel: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(3
				((ScriptID 730 1) cel: 0 stopUpd:)
				(soundFx stop:)
				(soundFx2 number: 902 loop: 1 play:)
				(waiter cel: 5 setCycle: EndLoop self)
			)
			(4
				(waiter
					posn: 104 143
					setLoop: (+ (waiter loop?) 1)
					setCycle: CycleTo 5 1 self
				)
			)
			(5 (self dispose:))
			(6 (waiter setCycle: EndLoop self))
			(7
				(waiter
					setCycle: Walk
					posn: 106 143
					setLoop: (+ (waiter loop?) 1)
					cel: 0
					setMotion: MoveTo 230 144 self
				)
			)
			(8
				(waiter
					setLoop: (+ (waiter loop?) 1)
					posn: 227 144
					cel: 0
					setCycle: EndLoop self
				)
				(soundFx2 number: 901 loop: 1 play:)
				((ScriptID 730 2) hide:)
			)
			(9
				(waiter dispose: setCycle: 0)
				((ScriptID 730 2) show: cel: 3 setCycle: BegLoop self)
			)
			(10
				(soundFx2 number: 902 loop: 1 play:)
				((ScriptID 730 2) stopUpd:)
				((ScriptID 730 1) stopUpd:)
				(self dispose:)
			)
			(11
				(ego setMotion: MoveTo 259 167 self)
			)
			(12 (ego setHeading: 315 self))
			(13
				(ego
					view: 734
					normal: 0
					setScale: 0
					cycleSpeed: 9
					setLoop: 1
					cel: 0
					posn: 251 168
					setCycle: EndLoop self
				)
			)
			(14
				(ego setLoop: 2 cel: 0 posn: 253 169 setCycle: EndLoop self)
			)
			(15
				(ego setLoop: 3 cel: 0 posn: 264 169 setCycle: EndLoop self)
			)
			(16
				(ego reset: 0 posn: 260 165)
				(ego put: 5)
				(proc730_3)
				(self dispose:)
			)
		)
	)
)

(instance waiter of Actor
	(properties
		x 92
		y 143
		view 735
		xStep 4
	)
)

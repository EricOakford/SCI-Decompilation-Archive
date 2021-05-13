;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include sci.sh)
(use Main)
(use Inset)
(use Sound)
(use Actor)
(use System)

(public
	triggerAndClock 0
)

(local
	local0
	local1
	local2
	[local3 2] = [33 18]
	[local5 5]
)
(instance triggerAndClock of Code
	(properties)
	
	(method (doit param1 param2)
		(if (& param1 $ff00)
			(= local0 (>> (= local0 (& param1 $f000)) $000c))
			(= local1 (>> (= local1 (& param1 $0f00)) $0008))
			(switch (= local2 (+ (* local0 100) (* 15 local1)))
				(815 (Bset 1))
				(1015 (Bset 2))
				(1115 (Bset 3))
				(200 (Bset 4))
				(300 (Bset 5) (= global111 15))
				(315 (Bset 6))
			)
			(curRoom
				setInset: clockInset (if (> argc 1) param2 else 0)
			)
			(= param1 (& param1 $00ff))
		)
		(= triggeredEvents (+ triggeredEvents param1))
	)
)

(instance saveVolume of Code
	(properties)
	
	(method (doit param1)
		(if (param1 handle?)
			(= [local5 (sounds indexOf: param1)] (param1 vol?))
			(if (> [local5 (sounds indexOf: param1)] 50)
				(param1 setVol: 50)
			)
		)
	)
)

(instance restoreVolume of Code
	(properties)
	
	(method (doit param1 &tmp temp0)
		(if (= temp0 [local5 (sounds indexOf: param1)])
			(param1 fade: temp0 1 5 0)
		)
	)
)

(instance sShowClock of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 40] temp40)
		(switch (= state newState)
			(0
				(quarterHand cel: local1 init:)
				(hourHand cel: local0 init:)
				(sounds eachElementDo: #perform saveVolume)
				(= ticks 60)
			)
			(1
				(clockSound
					number:
					(switch local1
						(0 23)
						(1 20)
						(2 21)
						(3 22)
					)
					play: self
				)
			)
			(2 (= ticks 60))
			(3
				(sounds eachElementDo: #perform restoreVolume)
				(if
					(= temp40
						(switch register
							(1000 -24319)
							(1245 4104)
							(145 8224)
							(245 12290)
							(else  0)
						)
					)
					((ScriptID 90 15)
						setReal: (ScriptID 90 15) 0 15 0 temp40
					)
				)
				(clockInset dispose:)
			)
		)
	)
)

(instance quarterHand of View
	(properties
		x 33
		y 19
		view 22
		loop 2
		priority 15
		signal $4010
	)
)

(instance hourHand of View
	(properties
		x 33
		y 19
		view 22
		loop 1
		priority 15
		signal $4010
	)
)

(instance clockInset of Inset
	(properties
		view 22
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: sShowClock 0 local2)
	)
	
	(method (dispose)
		(clockSound dispose:)
		(super dispose: &rest)
		(switch local2
			(1015
				((ScriptID 90 3) moveTo: -2)
			)
			(1115
				((ScriptID 90 1) goTo: 440)
				((ScriptID 90 2) goTo: 520)
				((ScriptID 90 4) moveTo: -1)
			)
			(1245
				((ScriptID 90 6) moveTo: -2)
			)
			(145
				((ScriptID 90 1) goTo: 520)
				((ScriptID 90 4) goTo: 430)
			)
			(215
				(if (== ((inventory at: 14) owner?) 520)
					((inventory at: 14) owner: 630)
				)
			)
			(245
				((ScriptID 90 4) goTo: 510)
				((ScriptID 90 1) goTo: 520)
			)
		)
		(if
		(and (Btst 1) (cast contains: (ScriptID 35 0)))
			((ScriptID 35 0) dispose:)
		)
		(if
		(and (Btst 2) (cast contains: (ScriptID 90 7)))
			((ScriptID 90 7) wandering: 0 setScript: 0 moveTo: -2)
		)
		(if
		(and (Btst 3) (cast contains: (ScriptID 90 5)))
			((ScriptID 90 5) wandering: 0 setScript: 0 moveTo: -2)
		)
		(if
		(and (Btst 5) (cast contains: (ScriptID 90 6)))
			((ScriptID 90 6) wandering: 0 setScript: 0 moveTo: -2)
		)
		(if
		(and (Btst 6) (cast contains: (ScriptID 90 1)))
			((ScriptID 90 1) wandering: 0 setScript: 0 room: -2)
		)
		(DisposeScript 22)
	)
	
	(method (handleEvent event)
		(event claimed: 1)
		(super handleEvent: event)
	)
)

(instance clockSound of Sound
	(properties
		flags $0001
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 335)
(include sci.sh)
(use Main)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm335 0
)

(local
	local0 =  2399
	local1
	local2
	local3
)
(procedure (localproc_0128)
	(user canControl: 1 canInput: 1)
	(theIconBar enable: 2 1)
	(theGame setCursor: 982 1)
	(theIconBar select: (theIconBar at: 2))
)

(procedure (localproc_015e)
	(user canControl: 0 canInput: 0)
	(theIconBar disable: 0 3 4 5 6 7 8)
)

(class LockDevice of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $5010
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		deviceNum 0
		openScript 0
		closeScript 0
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (not local1)
						(= local1 1)
						(digit1 init: setScript: sCountDown)
					)
					(cond 
						((== local2 deviceNum) (curRoom setScript: openScript 0 deviceNum) (++ local2))
						((== local2 (+ deviceNum 1)) (curRoom setScript: closeScript 0 deviceNum) (-- local2))
						((OneOf deviceNum 2 3 6 7) (curRoom setScript: closeScript 0 deviceNum))
						(else (messager say: noun 4 1 0))
					)
					(return 1)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance rm335 of Rm
	(properties
		noun 7
		picture 67
	)
	
	(method (init)
		(self setRegions: 350)
		(LoadMany 128 447 448)
		(super init: &rest)
		(centerPanel init:)
		(latchTop init:)
		(latchBottom init:)
		(knobTR init:)
		(knobTL init:)
		(knobBR init:)
		(knobBL init:)
		(doorTR init:)
		(doorTL init:)
		(doorBR init:)
		(doorBL init:)
		(cloakDevice init:)
		(cloakLight init: setCycle: Fwd)
		(curRoom setScript: sStart)
	)
)

(instance sStart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_015e)
				(= seconds 2)
			)
			(1 (messager say: 7 0 0 0 self))
			(2
				(theIconBar enable:)
				(user canControl: 1 canInput: 1)
				(theIconBar enable: 2 1)
				(theGame setCursor: 982 1)
				(theIconBar select: (theIconBar at: 2))
				(self dispose:)
			)
		)
	)
)

(instance sOpenLatchTop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_015e)
				(theMusic2 number: 108 setLoop: 1 play:)
				(latchTop setCel: 1)
				(= cycles 3)
			)
			(1
				(localproc_0128)
				(self dispose:)
			)
		)
	)
)

(instance sCloseLatchTop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_015e)
				(theMusic2 number: 108 setLoop: 1 play:)
				(latchTop setCel: 0)
				(= cycles 3)
			)
			(1
				(localproc_0128)
				(self dispose:)
			)
		)
	)
)

(instance sOpenLatchBottom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_015e)
				(theMusic2 number: 108 setLoop: 1 play:)
				(latchBottom setCel: 1)
				(= cycles 3)
			)
			(1
				(centerPanel hide:)
				(localproc_0128)
				(self dispose:)
			)
		)
	)
)

(instance sCloseLatchBottom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_015e)
				(theMusic2 number: 108 setLoop: 1 play:)
				(latchBottom setCel: 0)
				(= cycles 3)
			)
			(1
				(centerPanel show:)
				(localproc_0128)
				(self dispose:)
			)
		)
	)
)

(instance sTurnKnob of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_015e)
				(theMusic2 number: 232 setLoop: 1 play:)
				(switch register
					(2
						(if (== (knobTL cel?) 0)
							(knobTL setCycle: End self)
						else
							(knobTL setCycle: Beg self)
						)
					)
					(7
						(if (== (knobTR cel?) 0)
							(knobTR setCycle: End self)
						else
							(knobTR setCycle: Beg self)
						)
					)
					(3
						(if (== (knobBR cel?) 0)
							(knobBR setCycle: End self)
						else
							(knobBR setCycle: Beg self)
						)
					)
					(6
						(if (== (knobBL cel?) 0)
							(knobBL setCycle: End self)
						else
							(knobBL setCycle: Beg self)
						)
					)
				)
			)
			(1
				(localproc_0128)
				(self dispose:)
			)
		)
	)
)

(instance sOpenPanel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_015e)
				(theMusic2 number: 431 setLoop: 1 play:)
				(switch register
					(4
						(doorTL setCel: 1)
						(knobTL hide:)
					)
					(5
						(doorBR setCel: 1)
						(knobBR hide:)
					)
					(8
						(doorTR setCel: 1)
						(knobTR hide:)
					)
					(9
						(doorBL setCel: 1)
						(knobBL hide:)
					)
				)
				(= cycles 3)
			)
			(1
				(switch register
					(4 (doorTL setCel: 2))
					(5 (doorBR setCel: 2))
					(8 (doorTR setCel: 2))
					(9 (doorBL setCel: 2))
				)
				(= cycles 3)
			)
			(2
				(if (== register 9)
					(= local2 20)
					(curRoom setScript: sSlideLatches)
				)
				(localproc_0128)
				(self dispose:)
			)
		)
	)
)

(instance sClosePanel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_015e)
				(theMusic2 number: 431 setLoop: 1 play:)
				(switch register
					(4 (doorTL setCel: 1))
					(5 (doorBR setCel: 1))
					(8 (doorTR setCel: 1))
					(9 (doorBL setCel: 1))
				)
				(= cycles 3)
			)
			(1
				(switch register
					(4
						(doorTL setCel: 0)
						(knobTL show:)
					)
					(5
						(doorBR setCel: 0)
						(knobBR show:)
					)
					(8
						(doorTR setCel: 0)
						(knobTR show:)
					)
					(9
						(doorBL setCel: 0)
						(knobBL show:)
					)
				)
				(= cycles 3)
			)
			(2
				(localproc_0128)
				(self dispose:)
			)
		)
	)
)

(instance sSlideLatches of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_015e)
				(= local3 0)
				(= cycles 1)
			)
			(1
				(latchTop y: (- 37 local3))
				(latchBottom y: (+ 139 local3))
				(if (< (= local3 (+ local3 3)) 35) (= state 0))
				(= cycles 3)
			)
			(2
				(localproc_0128)
				(self dispose:)
			)
		)
	)
)

(instance sRemoveDevice of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(Palette palANIMATE 29 32 1 33 37 1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_015e)
				(cloakLight hide:)
				(cloakDevice hide:)
				(light1 init:)
				(light2 init:)
				(SolvePuzzle 212 200)
				(= cycles 3)
			)
			(1
				(glow1 init:)
				(glow2 init:)
				(= ticks 5)
			)
			(2
				(arc init: setCycle: Fwd)
				(theMusic2 number: 145 setLoop: -1 play:)
				(= seconds 2)
			)
			(3 (messager say: 4 0 0 0 self))
			(4
				(theMusic2 fade:)
				(ego get: 12)
				(localproc_0128)
				(curRoom newRoom: 330)
				(self dispose:)
			)
		)
	)
)

(instance sCountDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(digit2 init:)
				(hashmark init:)
				(digit3 init:)
				(digit4 init:)
				(theMusic1 number: 124 setLoop: -1 play:)
				(= cycles 1)
			)
			(1 (messager say: 8 0 0 0 self))
			(2
				(= local0 (- local0 1))
				(digit4 cel: (mod local0 10))
				(digit3 cel: (mod (/ local0 10) 10))
				(digit2 cel: (mod (/ local0 100) 10))
				(digit1 cel: (/ local0 1000))
				(if (> local0 0) (-- state))
				(= ticks 5)
			)
			(3
				(theGame handsOn:)
				(EgoDead 21)
			)
		)
	)
)

(instance latchTop of LockDevice
	(properties
		x 136
		y 37
		noun 6
		view 447
		loop 6
		priority 9
	)
	
	(method (init)
		(super init: &rest)
		(self
			openScript: sOpenLatchTop
			closeScript: sCloseLatchTop
		)
	)
)

(instance latchBottom of LockDevice
	(properties
		x 136
		y 139
		noun 6
		view 447
		loop 7
		priority 9
		deviceNum 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			openScript: sOpenLatchBottom
			closeScript: sCloseLatchBottom
		)
	)
)

(instance knobTL of LockDevice
	(properties
		x 92
		y 57
		noun 5
		view 447
		loop 4
		priority 8
		deviceNum 2
	)
	
	(method (init)
		(super init: &rest)
		(self openScript: sTurnKnob closeScript: sTurnKnob)
	)
)

(instance knobTR of LockDevice
	(properties
		x 205
		y 57
		noun 5
		view 447
		loop 4
		priority 8
		deviceNum 7
	)
	
	(method (init)
		(super init: &rest)
		(self openScript: sTurnKnob closeScript: sTurnKnob)
	)
)

(instance knobBR of LockDevice
	(properties
		x 205
		y 197
		z 100
		noun 5
		view 447
		loop 4
		priority 8
		deviceNum 3
	)
	
	(method (init)
		(super init: &rest)
		(self openScript: sTurnKnob closeScript: sTurnKnob)
	)
)

(instance knobBL of LockDevice
	(properties
		x 92
		y 197
		z 100
		noun 5
		view 447
		loop 4
		priority 8
		deviceNum 6
	)
	
	(method (init)
		(super init: &rest)
		(self openScript: sTurnKnob closeScript: sTurnKnob)
	)
)

(instance doorTL of LockDevice
	(properties
		x 67
		y 36
		noun 3
		view 447
		priority 7
		deviceNum 4
	)
	
	(method (init)
		(super init: &rest)
		(self openScript: sOpenPanel closeScript: sClosePanel)
	)
)

(instance doorTR of LockDevice
	(properties
		x 240
		y 36
		noun 3
		view 447
		loop 1
		priority 7
		deviceNum 8
	)
	
	(method (init)
		(super init: &rest)
		(self openScript: sOpenPanel closeScript: sClosePanel)
	)
)

(instance doorBR of LockDevice
	(properties
		x 240
		y 139
		noun 3
		view 447
		loop 3
		priority 7
		deviceNum 5
	)
	
	(method (init)
		(super init: &rest)
		(self openScript: sOpenPanel closeScript: sClosePanel)
	)
)

(instance doorBL of LockDevice
	(properties
		x 67
		y 139
		noun 3
		view 447
		loop 2
		priority 7
		deviceNum 9
	)
	
	(method (init)
		(self openScript: sOpenPanel closeScript: sClosePanel)
		(super init: &rest)
	)
)

(instance cloakDevice of Prop
	(properties
		x 152
		y 84
		noun 2
		view 447
		loop 9
		priority 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and (== local2 20) (not (curRoom script?)))
					(curRoom setScript: sRemoveDevice)
				else
					(messager say: 2 4 1 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cloakLight of Actor
	(properties
		x 152
		y 84
		noun 2
		view 447
		loop 8
		priority 5
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and (== local2 20) (not (curRoom script?)))
					(curRoom setScript: sRemoveDevice)
				else
					(messager say: 2 4 1 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance centerPanel of Prop
	(properties
		x 124
		y 68
		noun 1
		view 447
		loop 5
		priority 6
		signal $0010
	)
)

(instance light1 of Prop
	(properties
		x 220
		y 71
		view 447
		loop 10
	)
)

(instance light2 of Prop
	(properties
		x 87
		y 72
		view 447
		loop 10
		cel 1
	)
)

(instance glow1 of Prop
	(properties
		x 115
		y 69
		view 447
		loop 11
	)
)

(instance glow2 of Prop
	(properties
		x 192
		y 69
		view 447
		loop 11
		cel 1
	)
)

(instance arc of Prop
	(properties
		x 115
		y 69
		view 447
		loop 12
		signal $4000
	)
)

(instance digit1 of Prop
	(properties
		x 100
		y 146
		view 448
		cel 8
		priority 14
		signal $0010
	)
)

(instance digit4 of View
	(properties
		x 184
		y 146
		view 448
		cel 5
		priority 14
		signal $0010
	)
)

(instance digit3 of View
	(properties
		x 159
		y 146
		view 448
		cel 4
		priority 14
		signal $0010
	)
)

(instance hashmark of View
	(properties
		x 145
		y 145
		view 448
		loop 1
		priority 14
		signal $0010
	)
)

(instance digit2 of View
	(properties
		x 125
		y 146
		view 448
		cel 9
		priority 14
		signal $0010
	)
)

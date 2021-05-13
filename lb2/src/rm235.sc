;;; Sierra Script 1.0 - (do not remove this comment)
(script# 235)
(include sci.sh)
(use Main)
(use LBRoom)
(use Inset)
(use Feature)
(use Sound)
(use Actor)
(use System)

(public
	rm235 0
)

(local
	local0 =  1
)
(instance rm235 of LBRoom
	(properties
		modNum 230
		noun 24
		picture 235
	)
	
	(method (init)
		(Load rsVIEW 235)
		(super init:)
		(closeupBlotterLF init: setOnMeCheck: 1 2048)
		(closeupBlotterLB init: setOnMeCheck: 1 4096)
		(closeupBlotterRF init: setOnMeCheck: 1 16384)
		(closeupBlotterRB init: setOnMeCheck: 1 8192)
		(pencilHolder init:)
		(drawer init:)
		(restOfBlotter init: setOnMeCheck: 1 1024)
		(if (Btst 29) (keyInDrawerC init:))
		(InFirstPerson 1)
		(theGame handsOn:)
	)
	
	(method (dispose)
		(InFirstPerson 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 24 1 0 0 0 230)
			)
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sLiftMat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch register
					(0
						(if (cast contains: cornerUpLF)
							(cornerUpLF dispose:)
						else
							(cornerUpLF init:)
						)
					)
					(1
						(if (cast contains: cornerUpLB)
							(cornerUpLB dispose:)
						else
							(cornerUpLB init:)
						)
					)
					(2
						(if (cast contains: cornerUpRF)
							(cornerUpRF dispose:)
							(keyUnder dispose:)
						else
							(if (and (not (ego has: 5)) (not (Btst 29)))
								(keyUnder init:)
							)
							(cornerUpRF init:)
						)
					)
					(3
						(if (cast contains: cornerUpRB)
							(cornerUpRB dispose:)
						else
							(cornerUpRB init:)
						)
					)
				)
				(= cycles 1)
			)
			(1
				(cond 
					(
					(and (cast contains: cornerUpRF) (== register 2))
						(if (and (not (ego has: 5)) (not (Btst 29)))
							(messager say: 10 4 1 0 self 230)
						else
							(messager say: 10 4 2 0 self 230)
						)
					)
					(
					(and (cast contains: cornerUpRB) (== register 3)) (messager say: 10 4 2 0 self 230))
					(
					(and (cast contains: cornerUpLB) (== register 1)) (messager say: 10 4 2 0 self 230))
					(
					(and (cast contains: cornerUpLF) (== register 0)) (messager say: 10 4 2 0 self 230))
					(else (= cycles 1))
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sUseKeyOnDrawer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 0)
				(ego put: 5)
				((ScriptID 21 1) doit: 774)
				(Bset 29)
				(messager say: 9 16 0 0 self 230)
			)
			(1
				(openDrawer init:)
				(sFX number: 42 play:)
				(= cycles 1)
			)
			(2 (self dispose:))
		)
	)
)

(instance openDrawer of View
	(properties
		x 66
		y 120
		noun 20
		modNum 230
		view 235
		loop 1
		signal $4001
	)
	
	(method (init)
		(super init:)
		(if (not (ego has: 6)) (pressPass init:))
		(if (Btst 29) (keyInDrawerC dispose:))
		(keyInDrawerO init:)
	)
	
	(method (dispose)
		(super dispose:)
		(pressPass dispose:)
		(keyInDrawerO dispose:)
		(if (Btst 29) (keyInDrawerC init:))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(4
				(sFX number: 42 play:)
				(openDrawer dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cornerUpLF of View
	(properties
		x 69
		y 100
		noun 19
		modNum 230
		view 235
		priority 8
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cornerUpRF of View
	(properties
		x 200
		y 100
		noun 19
		modNum 230
		view 235
		cel 1
		priority 8
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cornerUpRB of View
	(properties
		x 174
		y 83
		noun 19
		modNum 230
		view 235
		cel 2
		priority 8
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cornerUpLB of View
	(properties
		x 97
		y 85
		noun 19
		modNum 230
		view 235
		cel 3
		priority 8
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance keyUnder of View
	(properties
		x 216
		y 118
		noun 21
		modNum 230
		view 235
		loop 3
		priority 9
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(ego get: 5)
				((ScriptID 21 0) doit: 774)
				(keyUnder dispose:)
			)
			(1 (curRoom setInset: inKey))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance keyInDrawerO of View
	(properties
		x 154
		y 155
		noun 22
		modNum 230
		view 235
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance keyInDrawerC of View
	(properties
		x 154
		y 132
		noun 23
		modNum 230
		view 235
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pressPass of View
	(properties
		x 92
		y 137
		noun 27
		modNum 15
		view 235
		loop 4
		cel 1
		priority 14
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				((ScriptID 22 0) doit: 1)
				(theGame points: 1 128)
				(ego get: 6)
				((ScriptID 21 0) doit: 775)
				(pressPass dispose:)
			)
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(1
				(curRoom setInset: inPressPass)
				(messager say: 27 1 0 0 0 15)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inPressPass of Inset
	(properties
		view 235
		loop 4
		x 85
		y 129
		disposeNotOnMe 1
		modNum 15
		noun 27
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				((ScriptID 22 0) doit: 1)
				(theGame points: 1 128)
				(ego get: 6)
				((ScriptID 21 0) doit: 775)
				(pressPass dispose:)
				(inPressPass dispose:)
			)
			(1
				(messager say: 27 1 0 0 0 15)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inKey of Inset
	(properties
		view 235
		loop 3
		cel 1
		x 187
		y 92
		hideTheCast 1
		disposeNotOnMe 1
		modNum 15
		noun 9
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 9 1 0 0 0 15))
			(4
				(ego get: 5)
				((ScriptID 21 0) doit: 774)
				(inKey dispose:)
				(keyUnder dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance closeupBlotterRF of Feature
	(properties
		y 109
		noun 10
		modNum 230
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sLiftMat 0 2)
			)
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance closeupBlotterRB of Feature
	(properties
		y 95
		noun 10
		modNum 230
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sLiftMat 0 3)
			)
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance closeupBlotterLB of Feature
	(properties
		y 95
		noun 10
		modNum 230
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sLiftMat 0 1)
			)
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance closeupBlotterLF of Feature
	(properties
		y 109
		noun 10
		modNum 230
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sLiftMat 0 0)
			)
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance restOfBlotter of Feature
	(properties
		y 109
		noun 26
		modNum 230
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pencilHolder of Feature
	(properties
		x 227
		y 78
		noun 8
		modNum 230
		nsTop 64
		nsLeft 219
		nsBottom 92
		nsRight 236
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance drawer of Feature
	(properties
		x 155
		y 137
		noun 9
		modNum 230
		nsTop 128
		nsLeft 67
		nsBottom 146
		nsRight 244
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((cast contains: openDrawer) (sFX number: 42 play:) (openDrawer dispose:))
					((Btst 29) (sFX number: 42 play:) (openDrawer init:))
					(local0 (messager say: 9 4 3 0 0 230))
					((not (ego has: 6)) (sFX number: 42 play:) (openDrawer init:))
					(else (messager say: 9 4 4 0 0 230))
				)
			)
			(16
				(curRoom setScript: sUseKeyOnDrawer)
			)
			(13
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
		number 42
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 235)
(include game.sh) (include "230.shm")
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
	drawerIsLocked =  TRUE
)
(instance rm235 of LBRoom
	(properties
		modNum 230
		noun N_INSET_DESK
		picture 235
	)
	
	(method (init)
		(Load RES_VIEW 235)
		(super init:)
		(closeupBlotterLF init: setOnMeCheck: ftrControl cLCYAN)
		(closeupBlotterLB init: setOnMeCheck: ftrControl cLRED)
		(closeupBlotterRF init: setOnMeCheck: ftrControl cYELLOW)
		(closeupBlotterRB init: setOnMeCheck: ftrControl cLMAGENTA)
		(pencilHolder init:)
		(drawer init:)
		(restOfBlotter init: setOnMeCheck: ftrControl cLGREEN)
		(if (Btst fDeskUnlocked)
			(keyInDrawerC init:)
		)
		(InFirstPerson TRUE)
		(theGame handsOn:)
	)
	
	(method (dispose)
		(InFirstPerson FALSE)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_INSET_DESK V_LOOK NULL 0 0 230)
			)
			(V_EXIT
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
							(if (and (not (ego has: iDeskKey)) (not (Btst fDeskUnlocked)))
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
						(if (and (not (ego has: iDeskKey)) (not (Btst fDeskUnlocked)))
							(messager say: N_LAURA_DESK V_DO C_FIND_KEY 0 self 230)
						else
							(messager say: N_LAURA_DESK V_DO C_BLOTTER_EMPTY 0 self 230)
						)
					)
					((and (cast contains: cornerUpRB) (== register 3))
						(messager say: N_LAURA_DESK V_DO C_BLOTTER_EMPTY 0 self 230)
					)
					((and (cast contains: cornerUpLB) (== register 1))
						(messager say: N_LAURA_DESK V_DO C_BLOTTER_EMPTY 0 self 230)
					)
					((and (cast contains: cornerUpLF) (== register 0))
						(messager say: N_LAURA_DESK V_DO C_BLOTTER_EMPTY 0 self 230)
					)
					(else (= cycles 1))
				)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance sUseKeyOnDrawer of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= drawerIsLocked FALSE)
				(ego put: iDeskKey)
				((ScriptID 21 1) doit: 774)
				(Bset fDeskUnlocked)
				(messager say: N_DRAWER V_DESK_KEY NULL 0 self 230)
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
		noun N_OPEN_DRAWER
		modNum 230
		view 235
		loop 1
		signal (| ignrAct stopUpdOn)
	)
	
	(method (init)
		(super init:)
		(if (not (ego has: iPressPass))
			(pressPass init:)
		)
		(if (Btst fDeskUnlocked)
			(keyInDrawerC dispose:)
		)
		(keyInDrawerO init:)
	)
	
	(method (dispose)
		(super dispose:)
		(pressPass dispose:)
		(keyInDrawerO dispose:)
		(if (Btst fDeskUnlocked)
			(keyInDrawerC init:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(InFirstPerson FALSE)
				(curRoom newRoom: 230)
			)
			(V_DO
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
		noun N_CORNER
		modNum 230
		view 235
		priority 8
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(InFirstPerson FALSE)
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
		noun N_CORNER
		modNum 230
		view 235
		cel 1
		priority 8
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(InFirstPerson FALSE)
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
		noun N_CORNER
		modNum 230
		view 235
		cel 2
		priority 8
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(InFirstPerson FALSE)
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
		noun N_CORNER
		modNum 230
		view 235
		cel 3
		priority 8
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(InFirstPerson FALSE)
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
		noun N_KEY_UNDER
		modNum 230
		view 235
		loop 3
		priority 9
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DESK_KEY
				(ego get: iDeskKey)
				((ScriptID 21 0) doit: 774)
				(keyUnder dispose:)
			)
			(V_LOOK
				(curRoom setInset: inKey)
			)
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
		noun N_KEY_IN_DRAWER_OPEN
		modNum 230
		view 235
		loop 2
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(InFirstPerson FALSE)
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
		noun N_KEY_IN_DRAWER_CLOSED
		modNum 230
		view 235
		loop 2
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(InFirstPerson FALSE)
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
		noun N_PRESS_PASS
		modNum 15
		view 235
		loop 4
		cel 1
		priority 14
		signal (| ignrAct fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				((ScriptID 22 0) doit: 1)
				(theGame points: 1 fGetPressPass)
				(ego get: iPressPass)
				((ScriptID 21 0) doit: 775)
				(pressPass dispose:)
			)
			(V_EXIT
				(InFirstPerson 0)
				(curRoom newRoom: 230)
			)
			(V_LOOK
				(curRoom setInset: inPressPass)
				(messager say: N_PRESS_PASS V_LOOK NULL 0 0 15)
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
		disposeNotOnMe TRUE
		modNum 15
		noun N_PRESS_PASS
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				((ScriptID 22 0) doit: 1)
				(theGame points: 1 fGetPressPass)
				(ego get: iPressPass)
				((ScriptID 21 0) doit: 775)
				(pressPass dispose:)
				(inPressPass dispose:)
			)
			(V_LOOK
				(messager say: N_PRESS_PASS V_LOOK NULL 0 0 15)
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
		hideTheCast TRUE
		disposeNotOnMe TRUE
		modNum 15
		noun N_DRAWER
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_DRAWER V_LOOK NULL 0 0 15)
			)
			(V_DO
				(ego get: iDeskKey)
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
		noun N_LAURA_DESK
		modNum 230
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sLiftMat 0 2)
			)
			(V_EXIT
				(InFirstPerson FALSE)
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
		noun N_LAURA_DESK
		modNum 230
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sLiftMat 0 3)
			)
			(V_EXIT
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
		noun N_LAURA_DESK
		modNum 230
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sLiftMat 0 1)
			)
			(V_EXIT
				(InFirstPerson FALSE)
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
		noun N_LAURA_DESK
		modNum 230
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sLiftMat 0 0)
			)
			(V_EXIT
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
		noun N_BLOTTER_REST
		modNum 230
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(InFirstPerson FALSE)
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
		noun N_PENCILS
		modNum 230
		nsTop 64
		nsLeft 219
		nsBottom 92
		nsRight 236
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(InFirstPerson FALSE)
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
		noun N_DRAWER
		modNum 230
		nsTop 128
		nsLeft 67
		nsBottom 146
		nsRight 244
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					((cast contains: openDrawer)
						(sFX number: 42 play:)
						(openDrawer dispose:)
					)
					((Btst fDeskUnlocked)
						(sFX number: 42 play:)
						(openDrawer init:)
					)
					(drawerIsLocked
						(messager say: N_DRAWER V_DO C_LOCKED 0 0 230)
					)
					((not (ego has: iPressPass))
						(sFX number: 42 play:)
						(openDrawer init:)
					)
					(else
						(messager say: N_DRAWER V_DO C_EMPTY 0 0 230)
					)
				)
			)
			(V_DESK_KEY
				(curRoom setScript: sUseKeyOnDrawer)
			)
			(V_EXIT
				(InFirstPerson FALSE)
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
		flags mNOPAUSE
		number 42
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1005)
(include game.sh) (include "1005.shm")
(use Main)
(use Osc)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm1005 0
)

(instance rm1005 of Room
	(properties
		noun N_ROOM
		picture 121
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(LoadMany RES_VIEW 667)
		(NormalEgo 0)
		(ego init: hide:)
		(if (Btst fInstalledDistCap)
			(lightPulse loop: 4)
			(lightCurve loop: 5)
			(statusLight cel: 0)
			(onOffText cel: 1)
		)
		(lightPulse init:)
		(lightCurve init:)
		(statusLight init:)
		(onOffText init:)
		(recepticle init: setOnMeCheck: 1 1024)
		(if (Btst fInstalledDistCap)
			(curRoom setScript: sOnLine)
		else
			(curRoom setScript: sOffLine)
		)
		(super init:)
		(walkHandler addToFront: self)
		(theGame handsOn:)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fInstalledDistCap)
					(messager say: noun theVerb C_ONLINE 0)
				else
					(messager say: noun theVerb C_OFFLINE 0)
				)
			)
			(else 
				(if (== prevRoomNum 1000)
					(curRoom newRoom: 1000)
				else
					(curRoom newRoom: 1001)
				)
			)
		)
	)
)

(instance sOffLine of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1
				(lightPulse loop: 0 cel: 0 setCycle: EndLoop self)
				(lightCurve loop: 2 cel: 0 setCycle: EndLoop)
			)
			(2
				(lightFlash loop: 1 cel: 0 init: setCycle: Oscillate 1 self)
			)
			(3
				(lightFlash dispose:)
				(lightPulse setCycle: Oscillate)
				(lightCurve setCycle: Oscillate)
				(theGame handsOn:)
			)
			(4
				(= next sInstallCap)
				(self dispose:)
			)
		)
	)
)

(instance sInstallCap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cap init:)
				(SolvePuzzle f1005InstallCap 100)
				(capTop
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 8
					setMotion: MoveTo 104 26 self
				)
			)
			(1
				(theMusic2 number: 651 setLoop: 1 play:)
				(Bset fInstalledDistCap)
				(ego put: iDistributorCap)
				(messager say: N_INSTALL_CAP NULL NULL 0 self)
			)
			(2
				(theGame handsOn:)
				(= next sOnLine)
				(self dispose:)
			)
		)
	)
)

(instance sOnLine of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cap init:)
				(capTop x: 104 y: 26 init:)
				(lightPulse loop: 4 cel: 0 setCycle: EndLoop self)
				(lightCurve loop: 5 cel: 0 setCycle: EndLoop)
				(statusLight cel: 0 x: 210 y: 43)
				(onOffText cel: 1)
			)
			(1
				(lightFlash loop: 6 cel: 0 init: setCycle: Oscillate 1 self)
			)
			(2
				(lightFlash dispose:)
				(lightPulse setCycle: Oscillate)
				(lightCurve setCycle: Oscillate)
				(theGame handsOn:)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance lightPulse of Prop
	(properties
		x 121
		y 95
		noun N_LIGHT
		view 667
		signal ignrAct
	)
)

(instance lightFlash of Prop
	(properties
		x 133
		y 96
		noun N_LIGHT
		view 667
		loop 1
		signal ignrAct
	)
)

(instance lightCurve of Prop
	(properties
		x 172
		y 93
		noun N_LIGHT
		view 667
		loop 2
		signal ignrAct
	)
)

(instance statusLight of View
	(properties
		x 224
		y 45
		noun N_LIGHT
		view 667
		loop 9
		cel 1
		signal ignrAct
	)
)

(instance onOffText of View
	(properties
		x 134
		y 139
		noun N_LIGHT
		view 667
		loop 3
		signal ignrAct
	)
)

(instance cap of Actor
	(properties
		x 113
		y 43
		noun N_CAP
		view 667
		loop 7
		priority 6
		signal (| ignrAct ignrHrz fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fInstalledDistCap)
					(messager say: noun theVerb C_ONLINE 0)
				else
					(messager say: noun theVerb C_OFFLINE 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance capTop of Actor
	(properties
		x 104
		y -10
		noun N_CAP
		view 667
		loop 8
		priority 8
		signal (| ignrAct ignrHrz fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fInstalledDistCap)
					(messager say: noun theVerb C_ONLINE 0)
				else
					(messager say: noun theVerb C_OFFLINE 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance recepticle of Feature
	(properties
		x 160
		y 95
		noun 3
		onMeCheck cLGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DIST_CAP
				(sOffLine cue:)
			)
			(V_LOOK
				(if (Btst fInstalledDistCap)
					(messager say: noun theVerb C_ONLINE 0)
				else
					(messager say: noun theVerb C_OFFLINE 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

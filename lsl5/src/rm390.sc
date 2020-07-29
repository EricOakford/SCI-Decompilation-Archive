;;; Sierra Script 1.0 - (do not remove this comment)
(script# 390)
(include game.sh)
(use Main)
(use LLRoom)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm390 0
)

(local
	cloud1X
	cloud2X
	doCount
	autoPilot
	larryCued
)
(instance rm390 of LLRoom
	(properties
		picture 390
	)
	
	(method (init)
		(super init:)
		(LoadMany FONT giantFont2 giantFont)
		(larry init: setCycle: EndLoop larry)
		(plane init: setScript: sPlane)
		(panel1 init: setCycle: Forward)
		(panel2 init: setCycle: Forward)
		(panel3 init: setCycle: Forward)
		(panel4 init: setCycle: Forward)
		(panel5 init: setCycle: Forward)
		(panel6 init: setCycle: Forward)
		(panel7 init: setCycle: Forward)
		(panel8 init: setCycle: Forward)
		(cloud init: setScript: sCloud)
		(cloud2 init: setScript: sCloud2)
		(frontCloud init: setScript: sFrontCloud)
		(frontCloud2 init: setScript: sFrontCloud2)
		(wheel init: setCycle: Forward)
		(wheel2 init: setCycle: Forward)
		(windows init:)
		(console init:)
		(throttle init:)
		(bigLarry init:)
		(thePlane init:)
		(self setScript: sPlaneCartoon)
		(SetFFRoom 310)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 390 0)
			)
			(verbTalk
				(TimePrint 390 1
					33 giantFont
					#title {You}
				)
			)
			(verbUse
				(TimePrint 390 2)
			)
			(verbZipper (TimePrint 390 3))
			(verbDo
				(switch (++ doCount)
					(1
						(TimePrint 390 4)
						(TimePrint 390 5 #at -1 185)
					)
					(2 (TimePrint 390 6))
					(3 (TimePrint 390 7))
					(4 (TimePrint 390 8))
					(5 (TimePrint 390 9))
					(else 
						(if (== (sPlaneCartoon state?) 5)
							(curRoom setScript: sAutoPilot)
						else
							(TimePrint 390 10)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (newRoom n)
		(theMusic stop:)
		(theMusic2 stop:)
		(super newRoom: n)
	)
)

(instance sPlane of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(plane setLoop: 1 cycleSpeed: 1 setCycle: Forward)
				(= cycles (Random 20 30))
			)
			(1
				(plane
					setLoop: 2
					setCel: 0
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(2
				(plane setLoop: 1 cycleSpeed: 1 setCycle: Forward)
				(= cycles (Random 20 30))
			)
			(3
				(plane
					setLoop: 3
					setCel: 0
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(4 (self init:))
		)
	)
)

(instance panel1 of Prop
	(properties
		x 14
		y 18
		description {the panel}
		view 390
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance panel2 of Prop
	(properties
		x 147
		y 18
		description {the panel}
		view 390
		loop 1
		cel 2
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance panel3 of Prop
	(properties
		x 98
		y 18
		description {the panel}
		view 390
		loop 2
		cel 1
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance panel4 of Prop
	(properties
		x 76
		y 92
		description {the panel}
		view 390
		loop 3
		cel 4
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance panel5 of Prop
	(properties
		x 192
		y 95
		description {the panel}
		view 390
		loop 4
		cel 2
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance panel6 of Prop
	(properties
		x 192
		y 18
		description {the panel}
		view 390
		loop 5
		cel 5
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance panel7 of Prop
	(properties
		x 283
		y 13
		description {the panel}
		view 390
		loop 6
		cel 2
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		detailLevel 4
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance panel8 of Prop
	(properties
		x 280
		y 83
		description {the panel}
		view 390
		loop 7
		cel 1
		priority 4
		signal (| ignrAct fixedLoop fixPriOn)
		detailLevel 4
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance wheel of Prop
	(properties
		x 92
		y 66
		description {the yoke}
		view 390
		loop 8
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 1
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance wheel2 of Prop
	(properties
		x 223
		y 65
		description {the yoke}
		view 390
		loop 8
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
		cycleSpeed 1
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance cloud of Actor
	(properties
		description {the cloud}
		view 392
		priority 2
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 390 11)
			)
			(verbDo
				(TimePrint 390 12)
				(TimePrint 390 13 #at -1 185)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance cloud2 of Actor
	(properties
		description {the cloud}
		view 392
		priority 2
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(cloud doVerb: theVerb theItem &rest)
	)
)

(instance frontCloud of Actor
	(properties
		description {the cloud}
		yStep 25
		view 392
		cel 3
		priority 2
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		xStep 25
	)
	
	(method (doVerb theVerb theItem)
		(cloud doVerb: theVerb theItem &rest)
	)
)

(instance frontCloud2 of Actor
	(properties
		description {the cloud}
		yStep 25
		view 392
		cel 3
		priority 2
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		xStep 25
	)
	
	(method (doVerb theVerb theItem)
		(cloud doVerb: theVerb theItem &rest)
	)
)

(instance sFrontCloud of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(frontCloud hide:)
				(= cycles (Random 2 4))
			)
			(1
				(frontCloud
					show:
					setCel: (Random 0 3)
					setStep: (+ (frontCloud xStep?) 2) (+ (frontCloud yStep?) 2)
				)
				(switch (Random 1 4)
					(1
						(frontCloud posn: 144 19 setMotion: MoveTo 0 67 self)
					)
					(2
						(frontCloud posn: 141 44 setMotion: MoveTo 0 11 self)
					)
					(3
						(frontCloud posn: 144 19 setMotion: MoveTo -24 40 self)
					)
					(4
						(frontCloud posn: 141 44 setMotion: MoveTo -24 40 self)
					)
				)
			)
			(2 (self init:))
		)
	)
)

(instance sFrontCloud2 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(frontCloud2 hide:)
				(= cycles (Random 2 4))
			)
			(1
				(frontCloud2
					show:
					setCel: (Random 0 3)
					setStep: (+ (frontCloud2 xStep?) 2) (+ (frontCloud2 yStep?) 2)
				)
				(switch (Random 1 4)
					(1
						(frontCloud2 posn: 177 20 setMotion: MoveTo 319 67 self)
					)
					(2
						(frontCloud2 posn: 177 20 setMotion: MoveTo 334 42 self)
					)
					(3
						(frontCloud2 posn: 182 43 setMotion: MoveTo 319 14 self)
					)
					(4
						(frontCloud2 posn: 182 43 setMotion: MoveTo 334 42 self)
					)
				)
			)
			(2 (self init:))
		)
	)
)

(instance sCloud of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cloud1X (Random 138 227))
				(cloud
					setCel: (Random 0 3)
					posn: cloud1X (Random 190 200)
					setStep: 10 (Random 10 15)
					setMotion: MoveTo cloud1X (Random 85 95) self
				)
			)
			(1 (self init:))
		)
	)
)

(instance sCloud2 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cloud2X (Random 249 340))
				(cloud2
					setCel: (Random 0 3)
					posn: cloud2X (Random 190 200)
					setStep: 10 (Random 10 15)
					setMotion: MoveTo cloud2X (Random 85 95) self
				)
			)
			(1 (self init:))
		)
	)
)

(instance theMusic3 of Sound)

(instance console of Feature
	(properties
		x 162
		y 55
		nsTop 35
		nsLeft 70
		nsBottom 75
		nsRight 255
		description {the console}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance throttle of Feature
	(properties
		x 158
		y 86
		nsTop 76
		nsLeft 131
		nsBottom 96
		nsRight 186
		description {the throttle}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance windows of Feature
	(properties
		x 157
		y 34
		nsLeft 3
		nsBottom 69
		nsRight 312
		description {the window}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance sPlaneCartoon of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(HandsOn)
				(User canControl: FALSE)
				(theIconBar disable: ICON_WALK)
				(= seconds 4)
			)
			(2
				(HandsOff)
				(TimePrint 390 14 #dispose self)
			)
			(3 (DisableIcons) (= ticks 30))
			(4
				(HandsOff)
				(TimePrint 390 15 #dispose self)
			)
			(5
				(DisableIcons)
				(= seconds 30)
			)
			(6
				(HandsOff)
				(TimePrint 390 16 #dispose self)
			)
			(7 (= ticks 30))
			(8 (TimePrint 390 17 #dispose self))
			(9 (= ticks 60))
			(10
				(= autoPilot TRUE)
				(self setScript: sAutoPilot)
			)
		)
	)
)

(instance sAutoPilot of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if autoPilot
					(self cue:)
				else
					(HandsOff)
					(TimePrint 390 18 #dispose self)
				)
			)
			(1 (= ticks 60))
			(2
				(if (not autoPilot) (SolvePuzzle 100))
				(plane setLoop: 1 setCel: 0 setScript: 0)
				(wheel setCycle: BegLoop)
				(wheel2 setCycle: BegLoop)
				(sCloud dispose:)
				(sCloud2 dispose:)
				(sFrontCloud dispose:)
				(sFrontCloud2 dispose:)
				(= cycles 8)
			)
			(3
				(frontCloud show: setCel: 1 posn: 56 39)
				(frontCloud2 show: setCel: 2 posn: 249 44)
				(= larryCued TRUE)
				(TimePrint 390 19 #dispose self)
			)
			(4
				(cloud
					show:
					setCel: 0
					posn: 245 138
					xStep: 2
					moveSpeed: 10
					setMotion: MoveTo 120 138
				)
				(cloud2
					show:
					setCel: 3
					posn: 310 173
					xStep: 2
					moveSpeed: 10
					setMotion: MoveTo 180 173
				)
				(plane setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(5 (= seconds 4))
			(6
				(TimePrint 390 20 33 giantFont2 70 200 30 1 #dispose self)
			)
			(7 (= ticks 120))
			(8 (TimePrint 390 21 #dispose self))
			(9 (curRoom newRoom: 310))
		)
	)
)

(instance plane of Actor
	(properties
		x 249
		y 137
		description {your plane}
		view 392
		loop 1
		priority 3
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(thePlane doVerb: theVerb &rest)
	)
)

(instance larry of Prop
	(properties
		x 70
		y 189
		description {yourself}
		view 391
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(bigLarry doVerb: theVerb &rest)
	)
	
	(method (cue)
		(if larryCued
			(self setCel: 2)
		else
			(self setCel: (Random 0 3))
			(StartTimer (Random 2 4) 1 larry)
		)
	)
)

(instance bigLarry of Feature
	(properties
		x 79
		y 149
		z 7
		nsTop 96
		nsBottom 189
		nsRight 158
		description {you}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 390 22)
			)
			(verbTalk
				(TimePrint 390 23)
			)
			(verbDo
				(TimePrint 390 24)
			)
			(verbUse
				(TimePrint 390 25)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance thePlane of Feature
	(properties
		x 238
		y 151
		z 9
		nsTop 95
		nsLeft 157
		nsBottom 189
		nsRight 319
		description {the plane}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 390 26)
			)
			(verbTalk
				(TimePrint 390 27)
			)
			(verbUse
				(TimePrint 390 28)
			)
			(verbDo
				(TimePrint 390 29)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

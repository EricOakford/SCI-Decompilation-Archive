;;; Sierra Script 1.0 - (do not remove this comment)
(script# 620)
(include sci.sh)
(use Main)
(use AnimDialog)
(use Talker)
(use Scaler)
(use Osc)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm620 0
	floTalker 13
	myRogTalker 15
	sUseComm 20
)

(local
	local0
	local1
	local2
	local3
	local4 =  1
)
(instance theMusic3 of Sound
	(properties)
)

(instance theMusic4 of Sound
	(properties)
)

(instance rm620 of Rm
	(properties
		noun 14
		picture 100
	)
	
	(method (init)
		(LoadMany 143 number)
		(LoadMany 128 22 18)
		(= style
			(switch prevRoomNum
				(640 12)
				(else  -32758)
			))
		(super init:)
		(ego edgeHit: 0)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						142
						19
						144
						47
						150
						149
						181
						176
						189
						319
						189
						319
						123
						281
						126
						258
						126
						189
						126
						177
						129
						227
						132
						241
						143
						239
						158
						232
						164
						213
						168
						177
						169
						118
						159
						100
						153
						80
						150
						49
						142
						0
						132
						0
						141
					yourself:
				)
				((Polygon new:)
					type: 3
					init:
						0
						113
						50
						110
						89
						113
						127
						105
						104
						94
						121
						91
						148
						96
						198
						88
						182
						77
						171
						70
						154
						77
						141
						80
						110
						75
						95
						66
						65
						69
						98
						85
						83
						87
						0
						73
						0
						112
					yourself:
				)
		)
		(if (!= global142 0)
			(pod init:)
			(podDoor init: setOnMeCheck: 1 (podDoor onMeCheck?))
			(mist1 init:)
			(mist2 init:)
			(stems init: setOnMeCheck: 1 (stems onMeCheck?))
			(path init: setOnMeCheck: 1 (path onMeCheck?))
			(bigStem init: setOnMeCheck: 1 (bigStem onMeCheck?))
			(chasm init: setOnMeCheck: 1 (chasm onMeCheck?))
			(if (== global142 1) (podHeat init:))
		)
		(switch prevRoomNum
			(640
				(if (Btst 91)
					(self setScript: sEnterHigh)
				else
					(self setScript: sEnterLow)
				)
			)
			(else 
				(if (Btst 109)
					(curRoom setScript: sFromShip)
				else
					(curRoom setScript: sNoMask)
				)
			)
		)
	)
	
	(method (doit)
		(if (not script)
			(cond 
				((& (= local3 (ego onControl: 1)) $0008) (curRoom setScript: sExitLow))
				((& local3 $0004) (curRoom setScript: sExitHigh))
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== (curRoom curPic?) 101)
					(messager say: 5 1 0 0)
				else
					(messager say: 14 1 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sYourDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(NormalEgo 553)
				(ego
					init:
					setLoop: 9
					cel: 0
					x: 119
					y: 75
					setCycle: End self
				)
			)
			(1
				(theMusic3 number: 260 setLoop: 1 play:)
				(puker1 init: setCycle: End self)
				(puker2 init: setCycle: End self)
				(puker3 init: setCycle: End self)
			)
			(2 0)
			(3 0)
			(4
				(puker1 setLoop: 3 setCel: 0)
				(puker2 setLoop: 1 setCel: 0)
				(puker3 setLoop: 0 setCel: 0)
				(= seconds 2)
			)
			(5
				(theMusic1 number: 102 setLoop: 1 play:)
				(theMusic3 number: 32 setLoop: 1 play:)
				(ego setLoop: 10 cel: 0 setCycle: End self)
			)
			(6 (= seconds 1))
			(7
				(theMusic1 number: 519 setLoop: 1 play:)
				(thePuke init: setCycle: End self)
			)
			(8
				(ego setLoop: 11 cel: 0 setCycle: End self)
			)
			(9 (= seconds 2))
			(10 (EgoDead 29))
		)
	)
)

(instance sFromShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic1 number: 30 setLoop: -1 play:)
				(= seconds 4)
			)
			(1
				(theMusic2 number: 260 setLoop: 1 play:)
				(slug init:)
				(NormalEgo 22)
				(ego
					init:
					ignoreActors: 1
					posn: 231 132
					setCycle: End self
				)
			)
			(2
				(ego setScript: sBreath)
				(if (and (!= global142 0) (not (Btst 29)))
					(myBut init: hide:)
					(pod setScript: sBeep)
				)
				(NormalEgo 18)
				(ego setHeading: 180)
				(theGame handsOn:)
				(theIconBar curIcon: (theIconBar at: 0))
				(theGame setCursor: 980)
				(self dispose:)
			)
		)
	)
)

(instance sNoMask of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 4)
			)
			(1
				(theMusic3 number: 260 setLoop: 1 play:)
				(NormalEgo 6)
				(ego
					init:
					ignoreActors: 1
					setScale: Scaler 100 87 181 123
					posn: 231 132
					setCycle: End self
				)
			)
			(2
				(NormalEgo 553 5)
				(ego
					scaleSignal: 0
					scaleX: 128
					scaleY: 128
					setHeading: 180
				)
				(= seconds 1)
			)
			(3 (ego setCycle: End self))
			(4 (= seconds 2))
			(5 (EgoDead 28))
		)
	)
)

(instance sExitLow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr 91)
				(curRoom newRoom: 640)
			)
		)
	)
)

(instance sExitHigh of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 91)
				(ego setMotion: MoveTo -20 100 self)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 640)
			)
		)
	)
)

(instance sEnterHigh of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (!= global142 0) (not (Btst 29)))
					(myBut init: hide:)
					(pod setScript: sBeep)
				)
				(NormalEgo 18 0)
				(ego
					edgeHit: 0
					init:
					ignoreActors: 1
					setScript: sBreath
					posn: 6 100
					setScale: Scaler 93 71 122 75
					setMotion: MoveTo 75 89 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterLow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (and (!= global142 0) (not (Btst 29)))
					(myBut init: hide:)
					(pod setScript: sBeep)
				)
				(NormalEgo 18 0)
				(ego
					init:
					ignoreActors: 1
					posn: -30 140
					setScale: Scaler 100 87 181 123
					setScript: sBreath
					setMotion: MoveTo 31 140 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLocateTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1 (if local0 (self dispose:)))
		)
	)
)

(instance sFinishLook of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1 (ego setCycle: Beg self))
			(2
				(NormalEgo 18)
				(ego setScale: Scaler 93 71 122 75 setHeading: 180)
				(ego setScript: sBreath)
				(self dispose:)
			)
		)
	)
)

(instance sMoveSlug of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 10)))
			(1 (slug show:) (= seconds 1))
			(2 (slug setCycle: End self))
			(3
				(slugEyes init: setCycle: Osc 3 self)
			)
			(4
				(slugEyes dispose:)
				(slug setLoop: 4 setCycle: End self)
			)
			(5 (slug dispose:))
		)
	)
)

(instance sBurnMe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (& $0040 (ego onControl: 1))
					(= cycles 1)
				else
					(ego setMotion: MoveTo 104 82 self)
				)
			)
			(1
				(ego
					scaleSignal: 0
					scaleX: 128
					scaleY: 128
					view: 553
					loop: 3
					cel: 1
					setCycle: End self
				)
			)
			(2
				(ego loop: 4 cel: 0 setCycle: End self)
				(theMusic1 number: 31 setLoop: 1 play:)
				(theMusic3 number: 102 setLoop: 1 play:)
			)
			(3 (= seconds 2))
			(4
				(if (< global145 3) (++ global145))
				(NormalEgo 18)
				(ego setScale: Scaler 93 71 122 75)
				(theMusic3 stop:)
				(theMusic1 number: 30 setLoop: -1 play:)
				(messager say: 12 4 0 global145 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUseComm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local2
					(if (or (== global142 0) (Btst 104)) 8 else 10)
				)
				(theMusic3 number: 603 setLoop: 1 play:)
				(ego view: 553 loop: 8 cel: 0 setCycle: End self)
			)
			(1
				(floTalker
					normal: 0
					keepWindow: 1
					curNoun: local2
					curVerb: 0
					curCase: 4
				)
				(= cycles 1)
			)
			(2
				(theGame handsOn:)
				(theIconBar select: (theIconBar at: 2))
				(theGame setCursor: 982)
				(messager say: local2 0 4 0 self)
			)
			(3
				(floTalker normal: 1 keepWindow: 0)
				(= local1
					(switch (floTalker whichSelect?)
						(1 1)
						(2 2)
						(3 3)
					)
				)
				(messager say: local2 0 local1 0 self)
			)
			(4 (ego setCycle: Beg self))
			(5
				(NormalEgo 18)
				(ego setHeading: 180)
				(if (not (if (== local1 1) (== local2 8)))
					(theGame handsOn:)
					(self dispose:)
				else
					(= seconds 1)
				)
			)
			(6
				(ego view: 6 setLoop: 0 cel: 15 setCycle: Beg self)
			)
			(7
				(ego dispose:)
				(= seconds 2)
			)
			(8
				(theGame handsOn:)
				(curRoom newRoom: 240)
			)
		)
	)
)

(instance sPodScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 103 78 self)
			)
			(1
				(ego
					scaleSignal: 0
					scaleX: 128
					scaleY: 128
					view: 553
					loop: 6
					cel: 4
					setCycle: End self
				)
			)
			(2 (= seconds 2))
			(3
				(cast eachElementDo: #hide)
				(ego dispose:)
				(curRoom drawPic: 101 100)
				(if (and (not (ego has: 19)) (not (Btst 29)))
					(frock init:)
				)
				(beltRight init: cycleSpeed: 16 setCycle: Fwd)
				(dashLights init: cycleSpeed: 8 setCycle: Fwd)
				(beltLeft init: cycleSpeed: 17 setCycle: Fwd)
				(theGame handsOn:)
				(= ticks 20)
			)
			(4
				(outside init: setOnMeCheck: 1 (outside onMeCheck?))
			)
			(5
				(theGame handsOff:)
				(if (not (Btst 29)) (= next sYourDead))
				(curRoom drawPic: 100 100)
				(cast eachElementDo: #show)
				(frock dispose:)
				(myBut dispose:)
				(beltRight dispose:)
				(dashLights dispose:)
				(beltLeft dispose:)
				(outside dispose:)
				(= cycles 1)
			)
			(6
				(if (Btst 29) (ego init: setScript: sFinishLook))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance frock of View
	(properties
		x 92
		y 123
		noun 3
		view 565
		loop 4
		signal $5000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(SolvePuzzle 223 10)
				(ego get: 19)
				(self dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance myBut of Prop
	(properties
		x 74
		y 140
		noun 1
		view 565
		loop 3
		priority 3
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(sBeep dispose:)
				(SolvePuzzle 224 35)
				(Bset 29)
				(self dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (hide)
		(if (== (curRoom curPic?) 101)
			(= cel 1)
		else
			(= cel 1)
			(super hide:)
		)
	)
	
	(method (show)
		(if (== (curRoom curPic?) 101)
			(= cel 0)
			(super show:)
		else
			(= cel 0)
		)
	)
)

(instance pod of Prop
	(properties
		x 109
		y 69
		z 20
		noun 12
		view 554
		loop 5
		signal $5000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (< (ego y?) 120)
					(if (== global142 1)
						(curRoom setScript: sBurnMe)
					else
						(curRoom setScript: sPodScript)
					)
				else
					(messager say: 4 4 0 0)
				)
			)
			(1 (messager say: 4 1 0 0))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance outside of Feature
	(properties
		x 180
		y 130
		onMeCheck $1000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (sPodScript cue:))
			(4 (sPodScript cue:))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mist1 of Prop
	(properties
		x 177
		y 156
		view 554
		loop 3
		cel 3
		cycleSpeed 15
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd)
	)
)

(instance mist2 of Prop
	(properties
		x 92
		y 189
		view 554
		loop 2
		cel 1
		cycleSpeed 15
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd)
	)
)

(instance slug of Prop
	(properties
		x 277
		y 139
		noun 16
		view 554
		priority 15
		signal $0010
		cycleSpeed 60
	)
	
	(method (init)
		(super init:)
		(self hide: setScript: sMoveSlug)
	)
)

(instance slugEyes of Prop
	(properties
		x 296
		y 135
		view 554
		loop 1
		cel 1
		priority 15
		signal $0010
		cycleSpeed 15
		detailLevel 3
	)
)

(instance puker1 of Actor
	(properties
		x 158
		y 83
		view 561
		loop 9
	)
)

(instance puker2 of Actor
	(properties
		x 84
		y 90
		view 561
		loop 8
	)
)

(instance puker3 of Actor
	(properties
		x 73
		y 72
		view 561
		loop 7
	)
)

(instance podHeat of Prop
	(properties
		x 136
		y 54
		noun 17
		view 553
		cel 1
		signal $5000
		detailLevel 3
	)
	
	(method (init)
		(super init:)
		(self setCycle: Fwd)
	)
)

(instance chasm of Feature
	(properties
		x 124
		y 113
		noun 2
		onMeCheck $0100
	)
)

(instance stems of Feature
	(properties
		x 24
		y 57
		noun 18
		onMeCheck $1000
	)
)

(instance bigStem of Feature
	(properties
		x 285
		y 59
		noun 19
		onMeCheck $0800
	)
)

(instance path of Feature
	(properties
		x 79
		y 154
		noun 9
		onMeCheck $2000
	)
)

(instance podDoor of Feature
	(properties
		x 120
		y 155
		z 100
		noun 9
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (< (ego y?) 120)
					(curRoom setScript: sPodScript)
				else
					(messager say: 4 1 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance thePuke of Actor
	(properties
		x 124
		y 56
		view 561
		loop 11
		priority 15
		signal $0010
	)
)

(instance dashLights of Prop
	(properties
		x 212
		y 41
		noun 11
		view 565
		cel 3
	)
)

(instance beltLeft of Prop
	(properties
		x 123
		y 95
		noun 15
		view 565
		loop 1
		cel 2
		cycleSpeed 15
	)
)

(instance beltRight of Prop
	(properties
		x 209
		y 94
		noun 15
		view 565
		loop 2
		cel 2
		cycleSpeed 20
	)
)

(instance floTalker of ChoiceTalker
	(properties
		x 10
		y 15
		view 1008
		signal $0010
		talkWidth 180
		keepWindow 1
		back 5
		textX 100
		normal 1
	)
	
	(method (init)
		(= font userFont)
		(super init: floBust floEyes floMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance floBust of Prop
	(properties
		view 1008
	)
)

(instance floEyes of Prop
	(properties
		nsTop 33
		nsLeft 39
		view 1008
		loop 2
		signal $4000
	)
)

(instance floMouth of Prop
	(properties
		nsTop 43
		nsLeft 45
		view 1008
		loop 1
		signal $4000
	)
)

(instance sBreath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 550 setLoop: 1 play: self)
			)
			(1
				(= state -1)
				(= seconds (Random 2 4))
			)
		)
	)
)

(instance sBeep of Script
	(properties)
	
	(method (dispose)
		(theMusic4 dispose:)
		(myBut dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (curRoom curPic?) 101) (myBut show:))
				(theMusic4
					number: 124
					setLoop: 1
					play: self
					setVol: (Min 127 (Max 40 (- 130 (ego distanceTo: pod))))
				)
			)
			(1
				(myBut hide:)
				(= state -1)
				(= seconds 2)
			)
		)
	)
)

(instance myRogTalker of Talker
	(properties
		x 10
		y 25
		view 556
		loop 5
		talkWidth 150
		back 5
		textX 120
		textY 2
	)
	
	(method (init)
		(= font userFont)
		(super init: rogBust 0 rogMouth &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
)

(instance rogBust of Prop
	(properties
		view 556
	)
)

(instance rogMouth of Prop
	(properties
		nsTop 18
		nsLeft 43
		view 556
		loop 2
	)
)

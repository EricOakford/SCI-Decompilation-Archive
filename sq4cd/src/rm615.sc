;;; Sierra Script 1.0 - (do not remove this comment)
(script# 615)
(include game.sh)
(use Main)
(use ulence)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use System)

(public
	rm615 0
)

(local
	talkedToBartender
	local1
	local2
	local3
)
(instance rm615 of SQRoom
	(properties
		picture 615
	)
	
	(method (init)
		(switch prevRoomNum
			(610
				(ego x: -5 y: 127)
				(music stop:)
				(globalSound fade: 127 10 10 0)
				(self setScript: climbDOWNstairs)
			)
			(else 
				(ego x: -5 y: 127)
				(globalSound vol: 127 number: 804 loop: -1 playBed:)
				(self setScript: climbDOWNstairs)
			)
		)
		(ego init: show: illegalBits: 0 ignoreActors: 1)
		(theWindow init:)
		(stairs init:)
		(door init:)
		(bar init:)
		(stage init:)
		(barTender
			init:
			setPri: 14
			posn: (Random 104 210) 183
			setLoop: (Random 2 3)
			setScript: barTenderScript
		)
		(band init: setCycle: Forward)
		(if (not (ego has: iMatches)) (theMatches init:))
		(self setRegions: ULENCE)
		(daFloor init:)
		(daRoom init:)
		(writing init:)
		(super init:)
		(if (not (Btst fKickBikes))
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 118 21 167 8 174 74 183 86 168 232 168 242 182
							299 160 269 147 259 148 218 128 203 144 121 144
							105 127 77 149 63 141 39 155 28 150 0 104 0 0
							319 0 319 189 0 189
						yourself:
					)
			)
			(mono1 init:)
			(mono2 init:)
			(mono3 init:)
		else
			(self
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 118 22 161 21 169 68 183 248 183 299 160 269 147
							259 148 218 128 203 144 161 144 121 144 105 127 77 149
							65 144 35 157 0 104 0 0 319 0 319 189 0 189
						yourself:
					)
			)
		)
		(LoadMany VIEW 618 620 619)
	)
	
	(method (doit)
		(cond 
			(script 0)
			(
			(and (not (Btst fKickBikes)) (ego inRect: 87 155 210 180)) (self setScript: lookMonoGuys))
			((& (ego onControl: origin) cCYAN) (HandsOff) (= local2 1) (self setScript: exitScript))
		)
		(super doit: &rest)
		(if (> (ego y?) 176)
			(ego setPri: 13)
		else
			(ego setPri: -1)
		)
	)
)

(instance climbDOWNstairs of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (HandsOff) (= cycles 1))
			(1
				(ego setMotion: MoveTo 27 159 self)
			)
			(2 (= seconds 2))
			(3
				(if (not (ulence beenInBar?))
					(narrator say: 1)
					(ulence beenInBar: TRUE)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance lookMonoGuys of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(if local1
					(= local1 0)
					(self cue:)
				else
					(ego setMotion: PolyPath 117 159 self)
				)
			)
			(1 (ego setHeading: 135 self))
			(2
				(mono1 setCycle: EndLoop)
				(mono2 setCycle: EndLoop)
				(mono3 setCycle: EndLoop)
				(= seconds 4)
			)
			(3
				(globalSound fade: 85 10 10 0)
				(curRoom newRoom: 620)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(if local2
					(self cue:)
				else
					(ego setMotion: PolyPath 34 160 self)
				)
			)
			(1
				(ego setMotion: MoveTo -5 127 self)
			)
			(2
				(globalSound fade: 70 10 10 0)
				(curRoom newRoom: 610)
			)
		)
	)
)

(instance barTenderScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= seconds (Random 2 5)))
			(1
				(barTender
					setCycle: Walk
					setLoop: -1
					setMotion: MoveTo (Random 104 210) 183 self
				)
			)
			(2
				(if (> (Random 0 100) 80)
					(self init:)
				else
					(= cycles (Random 2 6))
				)
			)
			(3
				(if (> (Random 0 100) 50)
					(barTender setLoop: 3)
				else
					(barTender setLoop: 2)
				)
				(self init:)
			)
		)
	)
)

(instance getMatches of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst fKickBikes)
					(ego setMotion: PolyPath 89 181 self)
				else
					(ego setMotion: PolyPath 102 174 self)
				)
			)
			(1
				(if (Btst fKickBikes)
					(ego setHeading: 180 self)
				else
					(= local1 1)
					(narrator say: 2)
					(curRoom setScript: lookMonoGuys)
					(self dispose:)
				)
			)
			(2
				(SolvePuzzle fGotMatches 5)
				(ego get: iMatches)
				(theMatches dispose:)
				(narrator say: 3)
				(NormalEgo (ego loop?) 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance talkToBartender of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 152 184 self)
			)
			(1
				(ego setLoop: 2)
				(barTender setScript: 0)
				(cond 
					((< (barTender x?) 147)
						(barTender
							setLoop: 0
							setCycle: Forward
							moveSpeed: 1
							cycleSpeed: 6
							setMotion: MoveTo 152 187 self
						)
					)
					((> (barTender x?) 169)
						(barTender
							setLoop: 1
							setCycle: Forward
							moveSpeed: 1
							cycleSpeed: 6
							setMotion: MoveTo 152 187 self
						)
					)
					(else (= seconds 1))
				)
			)
			(2
				(barTender setLoop: 3 setCycle: 0 setMotion: 0)
				(= seconds 1)
			)
			(3
				(tBARTENDER tStyle: 1 say: 4 self)
			)
			(4
				(tROGER tStyle: 0 say: 5 self)
			)
			(5
				(NormalEgo)
				(HandsOn)
				(barTender setScript: barTenderScript)
				(self dispose:)
			)
		)
	)
)

(instance daFloor of Sq4Feature
	(properties
		x 159
		y 112
		nsTop 36
		nsBottom 189
		nsRight 319
		onMeCheck (| ISNOTHIDDEN NEARCHECK)
		lookStr 6
	)
)

(instance daRoom of Sq4Feature
	(properties
		x 159
		y 112
		nsTop 36
		nsBottom 189
		nsRight 319
		onMeCheck $0020
		lookStr 7
	)
)

(instance writing of Sq4Feature
	(properties
		x 30
		y 117
		nsTop 97
		nsLeft 26
		nsBottom 138
		nsRight 34
		sightAngle 90
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(if (!= (++ local3) 6)
					(narrator say: 8)
				else
					(narrator say: 8)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mono1 of Sq4Prop
	(properties
		x 193
		y 180
		z 10
		view 620
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_TALK
				(curRoom setScript: lookMonoGuys)
			)
			(V_DO
				(curRoom setScript: lookMonoGuys)
			)
			(V_SMELL
				(narrator modNum: 702 say: 18)
				(curRoom setScript: lookMonoGuys)
			)
			(V_TASTE
				(narrator modNum: 702 say: 18)
				(curRoom setScript: lookMonoGuys)
			)
			(V_LOOK (narrator say: 10))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mono2 of Sq4Prop
	(properties
		x 118
		y 180
		z 10
		view 620
		loop 2
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(curRoom setScript: lookMonoGuys)
			)
			(V_TALK
				(curRoom setScript: lookMonoGuys)
			)
			(V_LOOK (narrator say: 11))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mono3 of Sq4Prop
	(properties
		x 157
		y 182
		z 10
		view 620
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(curRoom setScript: lookMonoGuys)
			)
			(V_TALK
				(curRoom setScript: lookMonoGuys)
			)
			(V_LOOK (narrator say: 12))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance stage of Sq4Feature
	(properties
		x 163
		y 118
		nsTop 99
		nsLeft 117
		nsBottom 138
		nsRight 210
		sightAngle 180
		onMeCheck FARCHECK
		lookStr 13
	)
)

(instance bar of Sq4Feature
	(properties
		x 158
		y 176
		nsTop 164
		nsLeft 73
		nsBottom 188
		nsRight 243
		sightAngle 180
		onMeCheck NEARCHECK
		lookStr 14
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_TALK
				(++ talkedToBartender)
				(if (not (Btst fMetBikers))
					(narrator say: 19)
					(curRoom setScript: lookMonoGuys)
				else
					(switch talkedToBartender
						(1
							(curRoom setScript: talkToBartender)
						)
						(else  (narrator say: 20))
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Sq4Feature
	(properties
		x 29
		y 127
		nsTop 94
		nsLeft 6
		nsBottom 160
		nsRight 53
		sightAngle 180
		onMeCheck $4000
		lookStr 29
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(curRoom setScript: exitScript)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theMatches of Sq4View
	(properties
		x 90
		y 193
		z 20
		nsTop 170
		nsLeft 87
		nsBottom 176
		nsRight 94
		view 620
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 15))
			(V_DO
				(curRoom setScript: getMatches)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance barTender of Sq4Actor
	(properties
		view 618
		signal ignrAct
		illegalBits $0000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 17))
			(V_DO (narrator say: 18))
			(V_TALK
				(++ talkedToBartender)
				(if (not (Btst fMetBikers))
					(narrator say: 19)
					(curRoom setScript: lookMonoGuys)
				else
					(switch talkedToBartender
						(1
							(curRoom setScript: talkToBartender)
						)
						(else  (narrator say: 20))
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance band of Sq4Prop
	(properties
		x 160
		y 115
		view 619
		signal (| ignrAct ignrHrz)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 22))
			(V_DO (narrator say: 23))
			(V_TALK (narrator say: 24))
			(V_SMELL (narrator say: 25))
			(V_BUCKAZOID
				(if buckazoids
					(narrator say: 26)
				else
					(narrator say: 27)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theWindow of Sq4Feature
	(properties
		x 164
		y 61
		nsTop 52
		nsLeft 154
		nsBottom 70
		nsRight 175
		sightAngle 180
		lookStr 28
	)
)

(instance stairs of Sq4Feature
	(properties
		x 26
		y 128
		nsTop 91
		nsLeft 6
		nsBottom 165
		nsRight 46
		sightAngle 180
		onMeCheck ISNOTHIDDEN
		lookStr 29
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_DO
				(curRoom setScript: exitScript)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tROGER of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1008
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 27
		eyeOffsetY 21
	)
)

(instance tBARTENDER of Sq4Talker
	(properties
		z 400
		noun BARTENDER
		view 1618
		talkerNum BARTENDER
		mouthOffsetX 28
		mouthOffsetY 15
		eyeOffsetX 28
		eyeOffsetY 9
	)
)

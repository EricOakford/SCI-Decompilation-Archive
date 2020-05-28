;;; Sierra Script 1.0 - (do not remove this comment)
(script# 371)
(include game.sh)
(use Main)
(use mall)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use Osc)
(use PolyPath)
(use Polygon)
(use ForCount)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use System)

(public
	rm371 0
)

(local
	local0 =  1
	local1
	dressPrice =  60
	[local3 52]
)
(class Mannequin2 of Sq4Prop
	(properties
		direct 0
		lastcel 0
		dummyTime 0
		detail 0
	)
)

(instance rm371 of SQRoom
	(properties
		picture 371
		style FADEOUT
	)
	
	(method (init)
		(LoadMany VIEW 0 4 402 371 372 373 374 920)
		(LoadMany FONT 68 69)
		(music number: 0 stop:)
		(globalSound setVol: 127)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 321 70 296 70 291 70 273 69 230 60
						58 60 42 74 25 75 4 90 76 118 64 152 0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 189 155 189 153 176 161 159 193 153 274 120
						304 91 296 87 295 81 299 73 321 73
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						87 70 125 82 157 78 188 84 227 77 268 79 292 93
						229 116 189 102 154 113 123 101 87 115 20 89
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 61 158 182 113 197 112 208 116 199 139 95 182 67 184 55 172
					yourself:
				)
		)
		(ego posn: 130 230 setHeading: 0 ignoreActors: TRUE init:)
		(curRoom setScript: enterScript)
		(dummy1 init:)
		(if (>= (theGame detailLevel:) (dummy1 detail?))
			(dummy1 setCycle: Oscillate)
		else
			(dummy1 stopUpd:)
		)
		(dummy2 init:)
		(if (>= (theGame detailLevel:) (dummy2 detail?))
			(dummy2 setCycle: Oscillate)
		else
			(dummy2 stopUpd:)
		)
		(dummy3 init:)
		(if (>= (theGame detailLevel:) (dummy3 detail?))
			(dummy3 setCycle: Oscillate)
		else
			(dummy3 stopUpd:)
		)
		(dummy4 init:)
		(if (>= (theGame detailLevel:) (dummy4 detail?))
			(dummy4 setCycle: Oscillate)
		else
			(dummy4 stopUpd:)
		)
		(model1 init:)
		(model2 init:)
		(super init:)
		(rmnProp init:)
		(if (== (ego view?) 402)
			(mall rFlag3: (| (mall rFlag3?) $0008))
		else
			(mall rFlag3: (& (mall rFlag3?) $fff7))
		)
		(wig init:)
		(clerk init: posn: 268 157 setCycle: Walk)
		(arm init: x: (+ (clerk x?) 2) y: (- (clerk y?) 28))
		(features
			add: wall theRack theRack1 theRack2 theArea changeRoom wigs wigs2
			eachElementDo: #init
			doit:
		)
		(self setRegions: MALL)
	)
	
	(method (doit &tmp temp0)
		(if
			(and
				(OneOf (ego view?) 0 4)
				(Btst fFlag29)
				(proc700_3 (ScriptID 700 0) 680 16)
			)
			(SolvePuzzle fWearDress 3)
		)
		(cond 
			(script (script doit:))
			(
				(and
					(OneOf (ego view?) 373 374)
					(!= (clerk script?) firstTimeScript)
					(>= (ego x?) 310)
				)
				(curRoom setScript: changeScript)
			)
			(
				(and
					(proc700_3 (ScriptID 700 0) 680 16)
					(!= (clerk script?) firstTimeScript)
					(>= (ego x?) 310)
				)
				(curRoom setScript: changeScript)
			)
			(
				(and
					(proc700_3 (ScriptID 700 0) 680 4)
					(!= (clerk script?) firstTimeScript)
					(>= (ego x?) 310)
				)
				(curRoom setScript: changeScript)
			)
			((== (ego edgeHit?) 3) (curRoom setScript: exitScript))
			((== (ego edgeHit?) 2) (ego setMotion: 0 posn: 317 (ego y?)))
		)
		(super doit:)
	)
	
	(method (dispose)
		(if (and local1 local0)
			(mall rFlag1: (| (mall rFlag1?) $0080))
		)
		(if
			(and
				(not (proc700_3 (ScriptID 700 0) 680 2))
				(proc700_3 (ScriptID 700 0) 680 1)
				(proc700_3 (ScriptID 700 0) 678 128)
			)
			(mall rFlag3: (| (mall rFlag3?) $0002))
		)
		(if
			(and
				(not (proc700_3 (ScriptID 700 0) 680 1))
				(proc700_3 (ScriptID 700 0) 678 128)
			)
			(mall rFlag3: (| (mall rFlag3?) $0001))
		)
		(ego ignoreActors: 0)
		(super dispose:)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 130 185 self)
			)
			(1 (self dispose:) (HandsOn))
		)
	)
)

(instance changeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 317 75 self)
			)
			(1
				(if (== (ego view?) 373)
					(if (proc700_3 (ScriptID 700 0) 680 4)
						(narrator say: 1)
						(if (proc700_3 (ScriptID 700 0) 680 8)
							(NormalEgo 2 402 14)
							(EgoHeadMove 14)
						else
							(NormalEgo 2 0 4)
							(EgoHeadMove 4)
						)
						(= cycles 10)
					else
						(narrator say: 2)
						(= local1 0)
						(if (proc700_3 (ScriptID 700 0) 680 8)
							(NormalEgo 2 402 14)
							(EgoHeadMove 14)
						else
							(NormalEgo 2 0 4)
							(EgoHeadMove 4)
						)
						(= cycles 10)
					)
					(mall rFlag3: (| (mall rFlag3?) $0010))
				else
					(narrator say: 3)
					(ego view: 373 setCycle: StopWalk 374)
					(= local1 1)
					(EgoHeadMove 374)
					(mall rFlag3: (& (mall rFlag3?) $ffef))
					(= cycles 3)
				)
			)
			(2
				(ego setMotion: MoveTo 285 72 self)
			)
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance shopScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 195 175 self)
			)
			(1
				(Face ego clerk)
				(if (Btst 33)
					(clerk setScript: firstTimeScript)
				else
					(clerk setScript: introScript)
				)
				(self dispose:)
			)
		)
	)
)

(instance introScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						(proc700_3 (ScriptID 700 0) 680 32)
						(proc700_3 (ScriptID 700 0) 680 64)
					)
					(narrator say: 4)
					(HandsOn)
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(1
				(if
					(and
						(proc700_3 (ScriptID 700 0) 680 32)
						(not (proc700_3 (ScriptID 700 0) 680 64))
					)
					(tClerk say: 20 self)
					(= state 7)
					(mall rFlag3: (| (mall rFlag3?) $0040))
				else
					(= cycles 1)
				)
			)
			(3 (tClerk say: 12 self))
			(5 (tRog say: 7 self))
			(7
				(tClerk say: 19 self)
				(mall rFlag3: (| (mall rFlag3?) $0020))
			)
			(9 (HandsOn) (self dispose:))
			(else  (= cycles 1))
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (== local1 1) (== local0 1))
					(self changeState: 6)
				else
					(= cycles 1)
				)
			)
			(1
				(if (clerk script?) ((clerk script?) dispose:))
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 10) self)
			)
			(2 (Face ego clerk self))
			(3 (tClerk say: 13 self))
			(4 (= cycles 1))
			(5
				(ego setMotion: MoveTo (ego x?) 240 self)
			)
			(6
				(curRoom newRoom: 370)
				(self dispose:)
			)
		)
	)
)

(instance firstTimeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (proc700_3 (ScriptID 700 0) 680 1)
					(self setScript: secondTalk self)
				else
					(self setScript: firstTalk self)
				)
			)
			(1
				(HandsOff)
				(clerk setMotion: MoveTo 344 185 self)
				(arm dispose:)
				(clerkSFX init: play:)
			)
			(2
				(clerk
					x: 322
					y: 72
					setPri: -1
					setMotion: PolyPath 95 148 self
				)
			)
			(4
				(clerk
					setLoop: 9
					setCycle: 0
					setMotion: PolyPath 196 114 self
				)
			)
			(5
				(clerkSFX stop:)
				(tClerk say: 7 self)
			)
			(7
				(clerk
					setLoop: 9
					setCycle: 0
					setMotion: PolyPath 281 75 self
				)
				(clerkSFX play:)
			)
			(8
				(clerkSFX stop:)
				(tClerk say: 8 self)
			)
			(10
				(clerk setMotion: PolyPath 317 75 self)
				(ego setMotion: PolyPath 317 75 self)
				(clerkSFX play:)
			)
			(11 (clerkSFX stop:))
			(13
				(clerk
					setLoop: 1
					ignoreActors:
					setMotion: PolyPath 273 74 self
				)
				(clerkSFX play:)
			)
			(14
				(clerkSFX stop:)
				(clerk setLoop: 0 setCel: 2)
				(tClerk say: 9 self)
			)
			(16
				(wig dispose:)
				(clerk setMotion: PolyPath 317 75 self)
				(clerkSFX play:)
			)
			(17
				(clerkSFX stop:)
				(= cycles 40)
			)
			(18
				(clerk
					setLoop: 3
					setCycle: Walk
					x: 344
					y: 185
					setPri: 13
					setMotion: MoveTo 268 157 self
				)
				(clerkSFX play:)
			)
			(19
				(clerkSFX stop:)
				(clerk setLoop: 5)
				(arm init: x: (+ (clerk x?) 2) y: (- (clerk y?) 28))
				(= cycles 1)
			)
			(20
				(ego
					view: 373
					sightAngle: 180
					setCycle: StopWalk 374
					setMotion: PolyPath 185 160 self
				)
				(EgoHeadMove 374)
			)
			(21
				(Face ego clerk)
				(arm setCycle: ForwardCounter (Random 1 3))
				(tClerk say: 10 self)
			)
			(23 (tRogette say: 5 self))
			(25
				(arm setCycle: ForwardCounter (Random 1 3))
				(tClerk say: 11 self)
				(theIconBar enable: ICON_ITEM ICON_INVENTORY)
				(= local1 1)
			)
			(26 (= cycles 1))
			(27
				(HandsOn)
				(ego ignoreActors: FALSE)
				(clerk setScript: bogusScript)
			)
			(else  (= cycles 1))
		)
	)
)

(instance firstTalk of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(arm init: x: (+ (clerk x?) 2) y: (- (clerk y?) 28))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(clerk setLoop: 5)
				(arm setCycle: ForwardCounter (Random 1 3))
				(tClerk say: 1 self)
			)
			(2
				(HandsOff)
				(tRog say: 1 self)
			)
			(4
				(arm setCycle: ForwardCounter (Random 1 3))
				(tClerk say: 2 self)
			)
			(6 (tClerk say: 3 self))
			(8 (tRog say: 2 self))
			(10
				(arm setCycle: ForwardCounter (Random 1 3))
				(tClerk say: 4 self)
			)
			(12 (tRog say: 3 self))
			(14
				(arm setCycle: ForwardCounter (Random 1 3))
				(tClerk say: 5 self)
			)
			(16 (tRog say: 4 self))
			(18
				(arm setCycle: ForwardCounter (Random 1 3))
				(tClerk say: 6 self)
			)
			(20
				(arm dispose:)
				(clerk setLoop: -1)
				(self dispose:)
			)
			(else  (= cycles 1))
		)
	)
)

(instance secondTalk of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(arm init: x: (+ (clerk x?) 2) y: (- (clerk y?) 28))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (proc700_3 (ScriptID 700 0) 680 2)
					(= state 1)
					(tClerk say: 17 self)
				else
					(tClerk say: 15 self)
				)
				(arm setCycle: ForwardCounter (Random 1 3))
			)
			(3 (tRog say: 6 self))
			(5
				(arm setCycle: ForwardCounter (Random 1 3))
				(tClerk say: 16 self)
			)
			(7
				(arm dispose:)
				(self dispose:)
			)
			(else  (= cycles 1))
		)
	)
)

(instance talkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(tClerk say: (+ (Random 0 7) 21) self)
			)
		)
	)
)

(instance bogusScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
		)
	)
)

(instance clerkHead of Sq4Prop
	(properties
		x 50
		y 50
		z -1000
		view 372
		loop 6
	)
)

(instance arm of Sq4Prop
	(properties
		x 50
		y 50
		view 372
		loop 8
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance dummy1 of Mannequin2
	(properties
		x 88
		y 69
		sightAngle 180
		view 371
		cel 10
		priority 6
		signal (| ignrAct fixPriOn)
		lastcel 6
		detail 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 5))
			(V_DO (narrator say: 6))
			(V_TASTE (narrator say: 7))
			(V_SMELL (narrator say: 8))
			(V_TALK (narrator say: 9))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance dummy2 of Mannequin2
	(properties
		x 221
		y 73
		sightAngle 180
		view 371
		loop 1
		cel 3
		priority 6
		signal (| ignrAct fixPriOn)
		lastcel 6
		detail 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 10))
			(V_DO (narrator say: 6))
			(V_TASTE (narrator say: 11))
			(V_SMELL (narrator say: 12))
			(V_TALK (narrator say: 18))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance dummy3 of Mannequin2
	(properties
		x 159
		y 70
		sightAngle 180
		view 371
		loop 2
		cel 4
		priority 6
		signal (| ignrAct fixPriOn)
		lastcel 7
		detail 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 14))
			(V_DO (narrator say: 15))
			(V_TASTE (narrator say: 16))
			(V_SMELL (narrator say: 17))
			(V_TALK (narrator say: 18))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance dummy4 of Mannequin2
	(properties
		x 31
		y 62
		sightAngle 180
		view 371
		loop 3
		cel 6
		priority 3
		signal (| ignrAct fixPriOn)
		lastcel 11
		detail 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 19))
			(V_DO
				(if
					(and
						(not (clerk script?))
						(not (curRoom script?))
						(OneOf (ego view?) 373 374)
					)
					(curRoom setScript: rogerDance)
				else
					(narrator say: 20)
				)
			)
			(V_TASTE (narrator say: 21))
			(V_SMELL (narrator say: 22))
			(V_TALK (narrator say: 23))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance verbUseScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(switch register
					(8
						(cond 
							((and (< buckazoids dressPrice) local1 local0) (tClerk say: 18))
							((and local1 local0)
								(tClerk say: 14)
								(if (< (= buckazoids (- buckazoids dressPrice)) 1)
									(ego put: iBuckazoid)
								)
								(SolvePuzzle fBuyDress 5)
								(mall rFlag3: (| (mall rFlag3?) $0004))
								(= local0 0)
							)
						)
					)
					(else  (self dispose:))
				)
				(= seconds 5)
			)
			(3 (self dispose:))
			(else  (= cycles 1))
		)
	)
)

(instance clerk of Sq4Actor
	(properties
		x 322
		y 72
		sightAngle 500
		view 372
		loop 5
		priority 13
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init: &rest)
		(if (proc700_3 (ScriptID 700 0) 680 1)
			(self posn: 275 116)
			(arm init: x: (+ (clerk x?) 2) y: (- (clerk y?) 28))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_BUCKAZOID
				(if
					(and
						(!= (rmnProp script?) verbUseScript)
						(!= (clerk script?) firstTimeScript)
					)
					(rmnProp setScript: verbUseScript 0 theVerb)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_LOOK (narrator say: 24))
			(V_TALK
				(cond 
					((proc700_3 (ScriptID 700 0) 680 4) (narrator say: 25))
					(
						(and
							(not (clerk script?))
							(not (curRoom script?))
							(not (proc700_3 (ScriptID 700 0) 680 4))
						)
						(self setScript: shopScript)
					)
					(
					(and ((self script?) script?) (user canInput:)) (((clerk script?) script?) cue:))
					(
						(and
							(not (proc700_3 (ScriptID 700 0) 680 4))
							(!= (rmnProp script?) verbUseScript)
							(!= (clerk script?) firstTimeScript)
							local1
							local0
							(OneOf (ego view?) 373 374)
						)
						(self setScript: talkScript)
					)
					(else 0)
				)
			)
			(V_DO (narrator say: 26))
			(V_TASTE (narrator say: 27))
			(V_SMELL (narrator say: 28))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance clerkSFX of Sound
	(properties
		number 834
		loop -1
	)
)

(instance rmnProp of Sq4Prop
	(properties
		x -20
		y -20
		view 920
	)
)

(instance wig of Sq4Prop
	(properties
		x 277
		y 44
		sightAngle 500
		view 372
		loop 10
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 29))
			(V_DO (narrator say: 30))
			(V_SMELL (narrator say: 31))
			(V_TASTE (narrator say: 32))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRack of Sq4Feature
	(properties
		x 100
		y 139
		nsTop 115
		nsLeft 81
		nsBottom 163
		nsRight 120
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 33))
			(V_DO (curRoom setScript: sRackDo))
			(V_SMELL (narrator say: 36))
			(V_TASTE (narrator say: 37))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRack1 of Sq4Feature
	(properties
		x 137
		y 125
		nsTop 107
		nsLeft 115
		nsBottom 154
		nsRight 159
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 33))
			(V_DO (curRoom setScript: sRackDo))
			(V_SMELL (narrator say: 36))
			(V_TASTE (narrator say: 37))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sRackDo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(narrator say: 34 self)
			)
			(1 (narrator say: 35 self))
			(2
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance theRack2 of Sq4Feature
	(properties
		x 177
		y 116
		nsTop 98
		nsLeft 153
		nsBottom 134
		nsRight 196
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 33))
			(V_DO (curRoom setScript: sRackDo))
			(V_SMELL (narrator say: 36))
			(V_TASTE (narrator say: 37))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theArea of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 38))
			(V_SMELL (narrator say: 39))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance model1 of Sq4Feature
	(properties
		x 99
		y 27
		nsTop 6
		nsLeft 89
		nsBottom 49
		nsRight 110
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 40))
			(V_DO
				(if (OneOf (ego view?) 373 374)
					(tRogette say: 10)
				else
					(tRog say: 10)
				)
			)
			(V_SMELL (narrator say: 41))
			(V_TASTE (narrator say: 42))
			(V_TALK (narrator say: 43))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance model2 of Sq4Feature
	(properties
		x 208
		y 27
		nsTop 6
		nsLeft 197
		nsBottom 49
		nsRight 219
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 44))
			(V_DO (narrator say: 45))
			(V_SMELL (narrator say: 46))
			(V_TASTE
				(if (OneOf (ego view?) 373 374)
					(tRogette say: 11)
				else
					(tRog say: 11)
				)
			)
			(V_TALK (narrator say: 47))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rogerDance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 112 113 self)
			)
			(1 (ego setHeading: 180 self))
			(2
				(if
				(or (== (ego view?) 373) (== (ego view?) 374))
					(tRogette say: 12)
				else
					(tRog say: 12)
				)
				(roger init: setCycle: Oscillate)
				(ego y: 1000)
				(arm setCycle: Oscillate)
				(= cycles 1)
			)
			(3 (= seconds 15))
			(4
				(ego y: 113)
				(roger dispose:)
				(arm setCycle: 0)
				(= cycles 3)
			)
			(5
				(if
				(or (== (ego view?) 373) (== (ego view?) 374))
					(tRogette say: 13)
				else
					(tRog say: 13)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance roger of Mannequin2
	(properties
		x 112
		y 113
		view 371
		loop 4
		signal ignrAct
		lastcel 7
	)
)

(instance wigs of Sq4Feature
	(properties
		x 265
		y 58
		nsTop 30
		nsLeft 250
		nsBottom 46
		nsRight 281
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 48))
			(V_DO (narrator say: 49))
			(V_TASTE (narrator say: 50))
			(V_SMELL (narrator say: 51))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance wall of Sq4Feature
	(properties
		x 312
		y 76
		nsTop 10
		nsLeft 306
		nsBottom 85
		nsRight 320
		sightAngle 180
	)
)

(instance changeRoom of Sq4Feature
	(properties
		x 297
		y 49
		nsTop 18
		nsLeft 289
		nsBottom 81
		nsRight 305
		sightAngle 180
		lookStr 52
	)
)

(instance wigs2 of Sq4Feature
	(properties
		x 55
		y 55
		nsTop 25
		nsLeft 45
		nsBottom 38
		nsRight 66
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 53))
			(V_DO (narrator say: 54))
			(V_TASTE (narrator say: 55))
			(V_SMELL (narrator say: 51))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tRog of Sq4Talker
	(properties
		z 400
		noun ROGER
		modNum 371
		view 1008
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 27
		eyeOffsetY 21
	)
)

(instance tRogette of Sq4Talker
	(properties
		z 400
		noun ROGER
		modNum 371
		view 1009
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 26
		eyeOffsetY 21
	)
)

(instance tClerk of Sq4Talker
	(properties
		z 400
		noun CLERK
		modNum 371
		view 1709
		talkerNum CLERK
		mouthOffsetX 18
		mouthOffsetY 63
		eyeOffsetX 28
		eyeOffsetY 25
		tStyle 1
	)
)

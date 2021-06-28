;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include game.sh) (include "230.shm")
(use Main)
(use LBRoom)
(use ExitFeature)
(use Inset)
(use Talker)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm230 0
	Laura 2
	Crodfoller 7
	ManWriting 37
)

(local
	noticeCount =  1
	local1 =  1
	local2 =  1
	local3 =  1
)
(instance rm230 of LBRoom
	(properties
		noun N_ROOM
		picture 230
		south 210
		vanishingX 150
		vanishingY 90
	)
	
	(method (init)
		(LoadMany RES_VIEW 231 232 233 238 1231 1230 830 829)
		(Load RES_SOUND 210)
		(ego
			signal: skipCheck
			init:
			viewer: (if (== prevRoomNum 235) 0 else checkScaling)
			normalize: 830
			setScale: Scaler 190 40 190 90
		)
		(switch prevRoomNum
			(south
				(curRoom setScript: sEnterNorth)
			)
			(26
				(ego x: 160 y: 160)
				((Inventory at: iNotebook) owner: ego)
				(Face ego crod)
				(self setScript: sDoTalking)
			)
			(235
				(ego
					view: 231
					setLoop: 0
					cel: 10
					setPri: 11
					x: 211
					y: 164
					setScale: Scaler 100 100 91 90
					setCycle: 0
				)
				(walkHandler addToFront: curRoom)
				(directionHandler addToFront: curRoom)
				(theGame handsOn:)
			)
			(else 
				(theGame handsOn:)
				(ego posn: 160 160)
			)
		)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 0
						319 189
						313 189
						313 164
						254 164
						242 158
						219 158
						207 165
						142 158
						142 148
						113 148
						103 158
						45 158
						5 167
						5 189
						0 189
						0 0
					yourself:
				)
		)
		(theMusic number: 210 loop: -1 flags: mNOPAUSE play:)
		(personS init: cycleSpeed: 10 setCycle: RandCycle)
		(personT init: cycleSpeed: 10 setCycle: RandCycle)
		(person1 init: setScript: sMoveIt)
		(person2 init: setScript: sMoveIt2)
		(gentsDoor approachVerbs: V_LOOK V_DO init:)
		(crod
			approachVerbs: V_LOOK V_DO V_TALK V_ASK
			setScript: sTypeAwayCrod
			init:
		)
		(trashcan init:)
		(blotter init:)
		(windowA init:)
		(window1 init:)
		(notice approachVerbs: V_DO init:)
		(aBulletin approachVerbs: V_DO init:)
		(herDesk setOnMeCheck: ftrControl cYELLOW init:)
		(chair init:)
		(hisDesk setOnMeCheck: ftrControl cLMAGENTA init:)
		(if (!= prevRoomNum 235)
			(southExitFeature init:)
		else
			(gentsDoor approachVerbs: 0)
			(crod approachVerbs: 0)
			(notice approachVerbs: 0)
			(aBulletin approachVerbs: 0)
		)
		((ScriptID 1881 2) x: 12 y: 95 textX: 120 textY: 0)
		((ScriptID 1896 7) x: 220 y: 80)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego cBLUE)
				(curRoom setScript: sExitSouth)
			)
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(walkHandler delete: curRoom)
		(directionHandler delete: curRoom)
		(DisposeScript RANDCYC)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			(inset
				(inset handleEvent: event)
			)
			(
				(and
					(& (event type?) direction)
					(== (theIconBar curIcon?) (theIconBar walkIconItem?))
					(!= (event message?) dirStop)
				)
				(event claimed: TRUE)
				(curRoom setScript: sStandUp)
			)
			((& (event type?) walkEvent)
				(super handleEvent: event)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(curRoom setScript: sStandUp)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sEnterNorth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					x: 160
					y: 290
					setHeading: 1
					setMotion: MoveTo 160 180 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setHeading: 180 setMotion: MoveFwd 80 self)
			)
			(2 (curRoom newRoom: 210))
		)
	)
)

(instance sDoTalking of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 21 0) doit: 261)
				(= cycles 4)
			)
			(1
				(crod
					loop: 1
					posn: 273 152
					setScript: 0
					setCycle: EndLoop self
				)
			)
			(2
				(messager say: N_MEET_CROD NULL NULL 0 self)
			)
			(3
				(theGame handsOn:)
				(crod setScript: sTypeAwayCrod)
				(= cycles 2)
			)
			(4 (self dispose:))
		)
	)
)

(instance sCopyProBack of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(crod setScript: 0)
				(theGame setCursor: 0)
				(= cycles 5)
			)
			(1
				(crod
					loop: 1
					posn: 273 152
					setScript: 0
					setCycle: EndLoop self
				)
			)
			(2 (crod setCycle: BegLoop self))
			(3
				(theGame handsOn:)
				(crod setScript: sTypeAwayCrod)
				(self dispose:)
			)
		)
	)
)

(instance sSitAtDesk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(if (Btst fEgoSits)
					(theGame handsOn:)
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(2
				(walkHandler addToFront: curRoom)
				(directionHandler addToFront: curRoom)
				(if (Btst fEgoSits)
					(= cycles 1)
				else
					(ego setMotion: PolyPath 170 161 self)
				)
			)
			(3
				(ego
					view: 231
					setLoop: 0
					cel: 0
					posn: 211 164
					setPri: 11
					setScale: Scaler 100 100 91 90
					setCycle: EndLoop self
				)
				(Bset fEgoSits)
			)
			(4
				(southExitFeature dispose:)
				(gentsDoor approachVerbs: 0)
				(crod approachVerbs: 0)
				(notice approachVerbs: 0)
				(aBulletin approachVerbs: 0)
				(= seconds 1)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sStandUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
			)
			(1
				(if (Btst fEgoSits)
					(= cycles 1)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(2
				(ego
					view: 231
					setLoop: 0
					posn: 211 164
					setCycle: BegLoop self
				)
			)
			(3
				(ego
					loop: 6
					posn: 173 164
					setScale: Scaler 190 40 190 90
					normalize: 830
				)
				(walkHandler delete: curRoom)
				(directionHandler delete: curRoom)
				(southExitFeature init:)
				(Bclr 30)
				(gentsDoor approachVerbs: V_LOOK V_DO)
				(crod approachVerbs: V_LOOK V_DO V_TALK V_ASK)
				(notice approachVerbs: V_DO)
				(aBulletin approachVerbs: V_DO)
				(= cycles 1)
			)
			(4
				(theGame handsOn:)
				(ego viewer: checkScaling)
				(self dispose:)
			)
		)
	)
)

(instance sLookInset of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setScript: sSitAtDesk self)
			)
			(2
				(messager say: N_INSET_DESK V_LOOK NULL 0 self)
			)
			(3
				(curRoom newRoom: 235)
			)
		)
	)
)

(instance sDigInTrash of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setScript: sSitAtDesk self)
			)
			(2
				(ego view: 231 loop: 2 posn: 148 161 setCycle: EndLoop self)
			)
			(3
				(theGame handsOn:)
				(if (and (not (ego has: iBaseball)) (not (ego has: 22)))
					(curRoom setInset: inBaseball self)
					(messager say: N_TRASHCAN V_DO C_BASEBALL_IN_TRASH)
				else
					(messager say: N_TRASHCAN V_DO C_GOT_BASEBALL 0 self)
				)
			)
			(4
				(theGame handsOff:)
				(= cycles 1)
			)
			(5 (ego setCycle: BegLoop self))
			(6
				(ego setLoop: 0 cel: 10 posn: 211 164)
				(= cycles 4)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGentsDoor of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: sStandUp self)
			)
			(1
				(theGame handsOff:)
				(ego setMotion: PolyPath 41 155 self)
			)
			(2
				(crod loop: 3 setScript: 0 setCycle: EndLoop self)
			)
			(3
				(crod posn: 277 153 cel: 0)
				(messager say: N_GENTS_DOOR V_DO NULL 0 self)
			)
			(4
				(theGame handsOn:)
				(crod setScript: sTypeAwayCrod)
				(self dispose:)
			)
		)
	)
)

(instance sTalkCrod of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(crod
					loop: 1
					posn: 273 152
					setScript: 0
					setCycle: EndLoop self
				)
			)
			(2
				(if local1
					(= local1 0)
					(messager say: N_CROD V_TALK 11 0 self)
				else
					(messager say: N_CROD V_TALK 12 0 self)
				)
			)
			(3 (crod setCycle: BegLoop self))
			(4
				(theGame handsOn:)
				(crod setScript: sTypeAwayCrod)
				(self dispose:)
			)
		)
	)
)

(instance sAskCrod of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(crod
					loop: 1
					posn: 273 152
					setScript: 0
					setCycle: EndLoop self
				)
			)
			(1
				(switch (= temp0 (curRoom setInset: (ScriptID 20 0)))
					(1026
						(messager say: N_CROD V_ASK 41 0 self)
					)
					(257
						(messager say: N_CROD V_ASK 19 0 self)
					)
					(773
						(messager say: N_CROD V_ASK 35 0 self)
					)
					(273
						(messager say: N_CROD V_ASK 31 0 self)
					)
					(1027
						((ScriptID 21 0) doit: 264)
						((ScriptID 21 0) doit: 520)
						((ScriptID 21 0) doit: 260)
						((ScriptID 21 1) doit: 518)
						(messager say: N_CROD V_ASK 42 0 self)
					)
					(259
						(messager say: N_CROD V_ASK 21 0 self)
					)
					(770
						(messager say: N_CROD V_ASK 37 0 self)
					)
					(269
						(messager say: N_CROD V_ASK 28 0 self)
					)
					(769
						(messager say: N_CROD V_ASK 36 0 self)
					)
					(261
						(messager say: N_CROD V_ASK 45 0 self)
					)
					(780
						(messager say: N_CROD V_ASK 39 0 self)
					)
					(516
						(messager say: N_CROD V_ASK 16 0 self)
					)
					(1028
						(messager say: N_CROD V_ASK 44 0 self)
					)
					(518
						((ScriptID 21 0) doit: 264)
						((ScriptID 21 0) doit: 520)
						((ScriptID 21 1) doit: 518)
						(messager say: N_CROD V_ASK 47 0 self)
					)
					(265
						(messager say: N_CROD V_ASK 48 0 self)
					)
					(774
						(messager say: N_CROD V_ASK 33 0 self)
					)
					(262
						(messager say: N_CROD V_ASK 23 0 self)
					)
					(515
						(messager say: N_CROD V_ASK 15 0 self)
					)
					(517
						((ScriptID 21 0) doit: 259)
						((ScriptID 21 0) doit: 258)
						(messager say: N_CROD V_ASK 17 0 self)
					)
					(270
						(messager say: N_CROD V_ASK 29 0 self)
					)
					(519
						(messager say: N_CROD V_ASK 43 0 self)
					)
					(771
						(messager say: N_CROD V_ASK 32 0 self)
					)
					(513
						((ScriptID 21 0) doit: 257)
						(messager say: N_CROD V_ASK 13 0 self)
					)
					(260
						(messager say: N_CROD V_ASK 22 0 self)
					)
					(775
						(messager say: N_CROD V_ASK 34 0 self)
					)
					(258
						(messager say: N_CROD V_ASK 20 0 self)
					)
					(514
						(messager say: N_CROD V_ASK 14 0 self)
					)
					(772
						(messager say: N_CROD V_ASK 38 0 self)
					)
					(520
						(messager say: N_CROD V_ASK 18 0 self)
					)
					(263
						(messager say: N_CROD V_ASK 24 0 self)
					)
					(271
						((ScriptID 21 0) doit: 258)
						(messager say: N_CROD V_ASK 30 0 self)
					)
					(266
						(messager say: N_CROD V_ASK 27 0 self)
					)
					(264
						(messager say: N_CROD V_ASK 25 0 self)
					)
					(else 
						(if (== -1 temp0)
							(= cycles 1)
						else
							(messager say: N_CROD V_ASK 26 0 self)
						)
					)
				)
			)
			(2 (crod setCycle: BegLoop self))
			(3
				(theGame handsOn:)
				(crod setScript: sTypeAwayCrod)
				(self dispose:)
			)
		)
	)
)

(instance sTypeAwayCrod of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(crod posn: 273 152 setLoop: 4 setCycle: RandCycle)
				(= seconds (Random 5 10))
			)
			(1
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance sMoveIt of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(person1
					setLoop: 1
					setCycle: Walk
					posn: 330 110
					setMotion: MoveTo 228 117 self
				)
			)
			(1
				(= seconds (Random 4 8))
			)
			(2
				(person1 setLoop: 0 setMotion: MoveTo 330 117 self)
			)
			(3
				(= seconds (Random 8 12))
			)
			(4
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance sMoveIt2 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 4 9)))
			(1
				(person2
					setLoop: 3
					posn: 330 108
					setCycle: Walk
					setMotion: MoveTo 241 108 self
				)
			)
			(2
				(person2 setLoop: 4 cycleSpeed: 8 setCycle: RandCycle)
				(= seconds (Random 4 7))
			)
			(3
				(person2 setCycle: 0)
				(= seconds (Random 1 3))
			)
			(4
				(person2
					setLoop: 2
					posn: 241 108
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 330 108 self
				)
			)
			(5 (= seconds (Random 4 8)))
			(6 (= state -1) (= cycles 1))
		)
	)
)

(instance crod of Prop
	(properties
		x 273
		y 152
		noun N_CROD
		approachX 242
		approachY 164
		view 232
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(curRoom setScript: sTalkCrod)
			)
			(V_ASK
				(curRoom setScript: sAskCrod)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance personS of Prop
	(properties
		x 152
		y 139
		noun N_MAN1
		view 233
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if local2
					(= local2 0)
					(messager say: N_MAN1 V_TALK 11)
				else
					(messager say: N_MAN1 V_TALK 12)
				)
			)
			(6 (messager say: 13 6 46))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance personT of Prop
	(properties
		x 85
		y 154
		noun N_MAN2
		view 233
		loop 6
		signal skipCheck
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_ASK
				(messager say: N_MAN2 V_ASK 46)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance person1 of Actor
	(properties
		x 330
		y 110
		noun N_MAN3
		view 233
		loop 1
	)
)

(instance person2 of Actor
	(properties
		x 330
		y 118
		noun N_MAN3
		view 233
		loop 3
		priority 6
		signal fixPriOn
	)
)

(instance inBaseball of Inset
	(properties
		view 238
		x 144
		y 121
		disposeNotOnMe TRUE
		modNum 15
		noun N_MEET_CROD
	)
	
	(method (init)
		(walkHandler delete: curRoom)
		(directionHandler delete: curRoom)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(walkHandler addToFront: curRoom)
		(directionHandler addToFront: curRoom)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_MEET_CROD V_BASEBALL NULL 0 0 15)
			)
			(V_DO
				((ScriptID 21 0) doit: 773)
				(inBaseball dispose:)
				(ego get: iBaseball)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance checkScaling of Code
	
	(method (doit)
		(return
			(cond 
				((and (> (ego y?) 165) local3)
					(= local3 0)
					(ego view: 829 setScale: Scaler 100 100 91 90)
				)
				((ego edgeHit?) (ego actions: 0))
				((and (<= (ego y?) 165) (not local3))
					(= local3 1)
					(ego view: 830 setScale: Scaler 190 40 190 90)
				)
				(else 0)
			)
		)
	)
)

(instance trashcan of Feature
	(properties
		x 164
		y 146
		noun N_TRASHCAN
		nsTop 140
		nsLeft 157
		nsBottom 152
		nsRight 172
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sDigInTrash)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance blotter of Feature
	(properties
		x 194
		y 117
		noun N_BLOTTER
		nsTop 115
		nsLeft 180
		nsBottom 120
		nsRight 208
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sLookInset)
			)
			(V_LOOK
				(curRoom setScript: sLookInset)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gentsDoor of Feature
	(properties
		x 19
		y 115
		noun N_GENTS_DOOR
		nsTop 78
		nsBottom 152
		nsRight 39
		sightAngle 40
		approachX 41
		approachY 155
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sGentsDoor)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance windowA of Feature
	(properties
		x 123
		y 95
		noun N_WINDOW
		nsTop 85
		nsLeft 109
		nsBottom 105
		nsRight 138
		sightAngle 40
	)
)

(instance window1 of Feature
	(properties
		x 176
		y 93
		noun N_WINDOW
		nsTop 85
		nsLeft 167
		nsBottom 101
		nsRight 185
		sightAngle 40
	)
)

(instance aBulletin of Feature
	(properties
		x 61
		y 98
		noun N_BULLETIN
		nsTop 82
		nsLeft 47
		nsBottom 114
		nsRight 76
		sightAngle 40
		approachX 83
		approachY 161
	)
	
	(method (doVerb theVerb)
		(notice doVerb: theVerb &rest)
	)
)

(instance notice of Feature
	(properties
		x 56
		y 99
		noun N_NOTICE
		nsTop 85
		nsLeft 52
		nsBottom 94
		nsRight 60
		approachX 83
		approachY 161
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fEgoSits)
					(messager say: N_NOTICE V_LOOK C_SITTING)
				else
					(switch noticeCount
						(1
							(messager say: N_BULLETIN V_LOOK 11)
							(++ noticeCount)
						)
						(2
							(messager say: N_BULLETIN V_LOOK 8)
							(++ noticeCount)
						)
						(3
							(messager say: N_BULLETIN V_LOOK 9)
							(++ noticeCount)
						)
						(else 
							(messager say: N_BULLETIN V_LOOK 10)
							(= noticeCount 1)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chair of Feature
	(properties
		x 195
		y 138
		noun N_INSET_DESK
		nsTop 119
		nsLeft 179
		nsBottom 158
		nsRight 210
	)
	
	(method (doVerb theVerb)
		(herDesk doVerb: theVerb &rest)
	)
)

(instance herDesk of Feature
	(properties
		x 195
		y 145
		noun N_LAURA_DESK
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: sLookInset)
			)
			(V_LOOK
				(curRoom setScript: sLookInset)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hisDesk of Feature
	(properties
		x 230
		y 145
		noun N_CROD_DESK
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 320
		cursor 11
		exitDir 3
		noun N_EXIT
	)
)

(instance Laura of Talker
	(properties
		x 12
		y 87
		view 1231
		loop 3
		talkWidth 160
		back 15
		textX 120
	)
	
	(method (init)
		(= font userFont)
		(super init: tLauraBust 0 tLauraMouth &rest)
	)
)

(instance tLauraMouth of Prop
	(properties
		nsTop 49
		nsLeft 41
		view 1231
	)
)

(instance tLauraBust of Prop
	(properties
		nsTop 10
		nsLeft 10
		view 1231
		loop 1
	)
)

(instance Crodfoller of Talker
	(properties
		x 220
		y 60
		view 1230
		loop 3
		talkWidth 150
		back 15
		textX -205
	)
	
	(method (init)
		(= font userFont)
		(super init: tCrodBust tCrodEyes tCrodMouth &rest)
	)
)

(instance tCrodMouth of Prop
	(properties
		nsTop 33
		nsLeft 17
		view 1230
	)
)

(instance tCrodEyes of Prop
	(properties
		nsTop 25
		nsLeft 21
		view 1230
		loop 2
	)
)

(instance tCrodBust of Prop
	(properties
		view 1230
		loop 1
	)
)

(instance MiscPeople of Narrator
	(properties
		x 10
		y 10
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)

(instance MidForground of Narrator
	(properties
		x 100
		y 50
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)

(instance ManWriting of Narrator
	(properties
		x 50
		y 50
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)

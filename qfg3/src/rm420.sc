;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
(include game.sh) (include "420.shm")
(use Main)
(use TellerIcon)
(use Inset)
(use PAvoid)
(use PolyPath)
(use Polygon)
(use Block)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Reverse)
(use Grooper)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm420 0
)

(local
	local0 = [0 -1 999]
	[local3 5]
	askedUhura
)
(procedure (InitCrowd)
	(crowd
		x: 92
		y: 189
		loop: 3
		ignoreActors: TRUE
		init:
		addToPic:
	)
	((ScriptID LAIBON_TALKER 1)
		view: 454
		loop: 1
		x: 160
		y: 117
		setScale: 350
		ignoreActors: TRUE
		init:
	)
	((ScriptID UHURA_TALKER 1)
		view: 971
		loop: 4
		cel: 6
		x: 28
		y: 133
		setScale: 350
		init:
		addToPic:
	)
	(simba
		loop: 4
		cel: 0
		x: 21
		y: 135
		setScale: 350
		init:
		addToPic:
	)
)

(instance rm420 of Room
	(properties
		noun N_ROOM
		picture 420
		vanishingY -60
	)
	
	(method (init)
		(HandsOff)
		(= [local3 0] @local0)
		(= [local3 1] 999)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						140 161
						100 160
						78 150
						80 137
						98 133
						121 131
						143 133
						155 141
						155 153
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 120
						269 103
						272 89
						262 88
						262 103
						238 103
						243 83
						267 74
						319 74
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						145 68
						146 84
						97 84
						94 76
						98 64
						211 65
						219 70
						226 82
						200 83
						167 83
						167 68
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						35 102
						0 96
						0 82
						68 76
						99 91
						93 104
						73 104
						79 84
						72 82
						71 98
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						223 64
						212 63
						200 58
						100 58
						49 60
						0 63
						0 0
						319 0
						319 60
						271 58
						267 64
						254 65
						239 64
						230 71
					yourself:
				)
		)
		(guard
			setScale:
			scaleX: 44
			scaleY: 44
			maxScale: 78
			loop: 8
			cel: 2
			stopUpd:
			approachVerbs: V_TALK
			init:
		)
		(egoActions init: ego @local0 @local3)
		(ego
			actions: egoActions
			normalize:
			setScale: 350
			noun: N_EGO_TELL
			init:
		)
		(chiefHut
			approachVerbs: (if (== origHeroType THIEF) V_LOOK else 0)
			init:
		)
		(if Night
			(fire setCycle: Forward init:)
		)
		(campfire init:)
		(tree init:)
		(fence init:)
		(mountain init:)
		(uhuraHutLook init:)
		(guestHut init:)
		(guestHutLook init:)
		(leaderDoor approachVerbs: V_LOOK init:)
		(cond 
			(
				(and
					(>= Day (+ enterSimbaniDay 3))
					(not (Btst fLeopardmanCaptured))
					(!= simbaniState 3)
					(not (Btst fStoleDrum))
				)
				(= simbaniState 2)
			)
			((and (Btst fLeopardmanCaptured) (not (Btst fDispelledLeopardman)) (!= simbaniState 5))
				(= simbaniState 4)
			)
			(
				(and
					(Btst fDispelledLeopardman)
					(not (Btst fGaveHorn))
					(not (ego has: iHorn))
					(== origHeroType FIGHTER)
					(!= simbaniState 7)
				)
				(= simbaniState 6)
			)
			(
				(and
					(== origHeroType FIGHTER)
					(not (Btst fGaveHorn))
					(ego has: iHorn)
					(!= simbaniState 9)
				)
				(= simbaniState 8)
			)
			(
				(and
					(Btst fDispelledLeopardman)
					(not (Btst fJohariReleased))
					(== brideState 0)
					(!= simbaniState 11)
				)
				(= simbaniState 10)
			)
			(
				(and
					(== brideState 1)
					(ego has: iFineSpear)
					(ego has: iRobe)
					(ego has: iSkins)
					(> ((inventory at: iSkins) amount?) 4)
					(!= simbaniState 13)
				)
				(= simbaniState 12)
			)
		)
		(if (not (Btst fFirstEnterSimbaniVillage))
			(curRoom style: PIXELDISSOLVE)
		)
		(super init:)
		(if (!= (cSound number?) 160)
			(cSound changeTo: 160)
		)
		(cond 
			((not (Btst fFirstEnterSimbaniVillage))
				(curRoom setScript: enterFirstTime)
			)
			((or (== prevRoomNum 620) (== prevRoomNum 630))
				(curRoom setScript: returnSpear)
			)
			((and (== prevRoomNum 470) (Btst fAfterMatch) (not (Btst fInitiationComplete)))
				(curRoom setScript: afterMatch)
			)
			((== prevRoomNum 500)
				(curRoom setScript: afterContest)
			)
			(
				(and
					(or (== heroType FIGHTER) (== heroType PALADIN))
					(Btst fGaveHorn)
					(not (Btst fAfterRace))
				)
				(curRoom setScript: startContest)
			)
			(
				(and
					(>= Day 5)
					(or (== timeODay TIME_NIGHT) (== timeODay TIME_SUNSET))
					(not (Btst fListenedToStory))
				)
				(curRoom setScript: enterStoryTell)
			)
			((== prevRoomNum 440) (curRoom setScript: enterFromUHut))
			(
				(and
					(== prevRoomNum 450)
					(== origHeroType THIEF)
					(== Day dayStoleMagicDrum)
					Night
				)
				(curRoom setScript: enterThief)
			)
			((== prevRoomNum 450)
				(curRoom setScript: enterFromLHut)
			)
			((== prevRoomNum 430)
				(curRoom setScript: enterFromEHut)
			)
			((== prevRoomNum 460)
				(curRoom setScript: enterFromSpRoom)
			)
			((== prevRoomNum 470)
				(curRoom setScript: enterFromWrRoom)
			)
			((== prevRoomNum 480)
				(curRoom setScript: enterFromPrisCage)
			)
			(else
				(curRoom setScript: enterRoom)
			)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((== (ego onControl: origin) cCYAN)
				(if (and Night (< timeODay TIME_MIDNIGHT))
					(curRoom setScript: enterUhHut)
				else
					(curRoom setScript: cantEnterUhHut)
				)
			)
			((guestHut onMe: ego)
				(curRoom newRoom: 430)
			)
			((== (ego onControl: origin) cGREEN)
				(if
					(and
						(not Night)
						(>= Day (+ enterSimbaniDay 3))
						(not (& simbaniState $0001))
					)
					(curRoom setScript: enterLeader)
				else
					(curRoom setScript: cantEnter)
				)
			)
			((and (<= (ego x?) 90) (< (ego y?) 70))
				(curRoom setScript: exitWest)
			)
			((< (ego x?) 10)
				(curRoom setScript: exitWest)
			)
			((>= (ego x?) 310)
				(curRoom setScript: exitEast)
			)
			((>= (ego y?) 180)
				(curRoom setScript: exitSouth)
			)
			(
				(and
					(< 90 (ego x?))
					(< (ego x?) 240)
					(< (ego y?) 70)
				)
				(curRoom newRoom: 480)
			)
			((and (> (ego x?) 239) (< (ego y?) 80))
				(curRoom newRoom: 470)
			)
			((and (< (ego x?) 78) (< (ego y?) 79))
				(curRoom newRoom: 460)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(LoadMany FALSE
			WANDER BLOCK UHURA_TALKER RAKEESH_TALKER YESUFU_TALKER
			LAIBON_TALKER STORY_TALKER INSET
		)
		(super dispose:)
	)
	
	(method (setInset theInset who reg)
		(if inset
			(inset dispose:)
		)
		(if (and argc theInset)
			(theInset
				init:
					(if (>= argc 2) who else 0)
					self
					(if (>= argc 3) reg else 0)
					&rest
			)
		)
	)
)

(instance egoActions of Teller
	
	(method (doChild)
		(if (== query -1)
			(cond 
				((ego inRect: 108 79 218 90)
					(ego addHonor: 3)
					(= query -2)
				)
				((ego inRect: 250 104 280 116)
					(if (and (> Day enterSimbaniDay) Night (< timeODay TIME_MIDNIGHT))
						(= askedUhura TRUE)
						(ego addHonor: 10)
						(= query -6)
					else
						(= query -7)
					)
				)
				(else
					(= query -3)
				)
			)
		)
		(return TRUE)
	)
)

(instance enterThief of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 221 y: 75 setMotion: PolyPath 230 90 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goTo620 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== heroType MAGIC_USER)
					(messager say: N_RETURN_DRUM V_DOIT C_RAKEESH_TELLS 0 self)
				else
					(self cue:)
				)
			)
			(1
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(DrawPic 0 HSHUTTER)
				(= cycles 3)
			)
			(2
				(messager say: N_CUE V_DOIT C_GO_TO_620 0 self)
			)
			(3
				(curRoom newRoom: 620)
			)
		)
	)
)

(instance goTo350 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(DrawPic 0 HSHUTTER)
				(= cycles 3)
			)
			(1
				(messager say: N_CUE V_DOIT C_GO_TO_350 0 self)
			)
			(2
				(= [egoStats HEALTH] (ego maxHealth:))
				(= [egoStats STAMINA] (ego maxStamina:))
				(= [egoStats MANA] (ego maxMana:))
				(curRoom newRoom: 350)
			)
		)
	)
)

(instance goTo280 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(DrawPic 0 HSHUTTER)
				(= cycles 3)
			)
			(1
				(messager say: N_CUE V_DOIT C_GO_TO_280 0 self)
			)
			(2
				(= [egoStats HEALTH] (ego maxHealth:))
				(= [egoStats STAMINA] (ego maxStamina:))
				(= [egoStats MANA] (ego maxMana:))
				(curRoom newRoom: 280)
			)
		)
	)
)

(instance goTo340 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #dispose eachElementDo: #delete)
				(DrawPic 0 HSHUTTER)
				(= cycles 3)
			)
			(1
				(messager say: N_CUE V_DOIT C_GO_TO_340 0 self)
			)
			(2
				(= [egoStats HEALTH] (ego maxHealth:))
				(= [egoStats STAMINA] (ego maxStamina:))
				(= [egoStats MANA] (ego maxMana:))
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance enterUhHut of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 267 103 self)
			)
			(1
				(curRoom newRoom: 440)
			)
		)
	)
)

(instance enterFromPrisCage of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 215 y: 68 setMotion: PolyPath 225 75 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cantEnterUhHut of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_CUE V_DOIT C_NO_ENTRY_UHURA 0 self)
			)
			(1
				(ego
					setMotion: PolyPath (- (ego x?) 5) (+ (ego y?) 5) self
				)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cantEnter of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(messager say: N_CUE V_DOIT C_NO_ENTRY_LAIBON 0 self)
			)
			(1
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 10) self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterLeader of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(++ simbaniState)
				(messager say: N_CUE V_DOIT C_ENTER_LEADER 0 self)
			)
			(1
				(curRoom newRoom: 450)
			)
		)
	)
)

(instance exitWest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (- (ego x?) 15) (ego y?) self)
			)
			(1
				(curRoom newRoom: 460)
			)
		)
	)
)

(instance exitEast of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (+ (ego x?) 15) (ego y?) self)
			)
			(1
				(curRoom newRoom: 470)
			)
		)
	)
)

(instance exitSouth of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 15) self)
			)
			(1
				(curRoom newRoom: 410)
			)
		)
	)
)

(instance enterRoom of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 190 y: 210 setMotion: PolyPath 190 170 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterFromUHut of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 265
					y: 105
					normalize:
					setMotion: MoveTo 270 115 self
				)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterFromLHut of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst fFlag125)
					(Bclr fFlag125)
					(self setScript: enterFromPrisCage self)
				else
					(ego
						x: 154
						y: 78
						normalize:
						setMotion: MoveTo 154 90 self
					)
				)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterFromEHut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 74 y: 99 normalize: setMotion: MoveTo 60 110 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterFromWrRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					x: 280
					y: 74
					normalize:
					setMotion: PolyPath 240 84 self
				)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterFromSpRoom of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego x: 65 y: 74 normalize: setMotion: MoveTo 85 82 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterFirstTime of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fFirstEnterSimbaniVillage)
				(= enterSimbaniDay Day)
				((ScriptID UHURA_TALKER 1)
					view: 969
					loop: 0
					cel: 0
					x: 144
					y: 133
					setScale: 300
					ignoreActors: TRUE
					init:
				)
				(simba x: 158 y: 135 setScale: 300 ignoreActors: TRUE init:)
				(= gGOwnerMoveSpeed (ego moveSpeed?))
				(ego
					normalize:
					x: 215
					y: 220
					setScale: 300
					setSpeed: 6
					setMotion: MoveTo 173 145 self
					ignoreActors: TRUE
				)
				((ScriptID RAKEESH_TALKER 1)
					view: 967
					setLoop: 3
					x: 254
					y: 220
					setCycle: Walk
					setScale: 300
					setLoop: lionGroop
					ignoreActors: 1
					setMotion: PolyPath 195 138 self
					init:
				)
			)
			(1 0)
			(2
				((ScriptID RAKEESH_TALKER 1) view: 962 setLoop: 1)
				(= ticks 90)
			)
			(3
				(messager say: N_RAKEESH V_DOIT C_FIRST_ENTRY 0 self)
			)
			(4
				(ego setSpeed: gGOwnerMoveSpeed)
				((ScriptID UHURA_TALKER 1)
					view: 971
					setCycle: Walk
					setAvoider: PAvoider
					setMotion: PolyPath 144 115 self
				)
			)
			(5
				(curRoom newRoom: 450)
			)
		)
	)
)

(instance enterStoryTell of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 420 hold:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								32 189
								72 128
								122 109
								217 109
								236 122
								235 189
							yourself:
						)
				)
				(crowd init: addToPic:)
				((ScriptID STORY_TALKER 1)
					x: 85
					y: 136
					setScale:
					scaleX: 66
					scaleY: 66
					init:
				)
				((ScriptID UHURA_TALKER 1)
					view: 971
					loop: 4
					cel: 1
					x: 160
					y: 119
					setScale:
					scaleX: 61
					scaleY: 61
					init:
					addToPic:
				)
				(simba
					loop: 4
					cel: 3
					x: 141
					y: 120
					setScale:
					scaleX: 64
					scaleY: 64
					init:
					addToPic:
				)
				(extra setScale: scaleX: 66 scaleY: 66 init: addToPic:)
				(extra2 setScale: scaleX: 66 scaleY: 66 init: addToPic:)
				(fire setCycle: Forward init:)
				(switch prevRoomNum
					(410 (ego x: 240 y: 210))
					(430 (ego x: 74 y: 99))
					(440 (ego x: 265 y: 105))
					(450 (ego x: 154 y: 75))
					(460 (ego x: -5 y: 160))
					(470 (ego x: 320 y: 160))
					(480 (ego x: 215 y: 68))
					(else  (ego x: 240 y: 210))
				)
				(ego
					setScale: 290
					setAvoider: PAvoider
					setMotion: PolyPath 240 140 self
				)
			)
			(1
				(Face ego (ScriptID STORY_TALKER 1))
				(= cycles 15)
			)
			(2
				(Bset fListenedToStory)
				(messager say: N_STORYTELLER V_DOIT C_STORY1 0 self)
			)
			(3
				((ScriptID STORY_TALKER 1) setCycle: EndLoop self)
			)
			(4
				(messager say: N_STORYTELLER V_DOIT C_STORY3 0 self)
			)
			(5
				((ScriptID STORY_TALKER 1) cel: 0 loop: 1 setCycle: EndLoop self)
			)
			(6
				(messager say: N_STORYTELLER V_DOIT C_STORY4 0 self)
			)
			(7
				((ScriptID STORY_TALKER 1) cel: 0 loop: 0 setCycle: EndLoop self)
			)
			(8
				((ScriptID STORY_TALKER 1) cel: 0 loop: 1 setCycle: EndLoop self)
			)
			(9
				(messager say: N_STORYTELLER V_DOIT C_STORY5 0 self)
			)
			(10
				((ScriptID STORY_TALKER 1) cel: 0 loop: 1 setCycle: EndLoop self)
			)
			(11
				(messager say: N_STORYTELLER V_DOIT C_END_STORY 0 self)
			)
			(12
				((ScriptID STORY_TALKER 1) cel: 0 loop: 2 setCycle: EndLoop self)
			)
			(13
				(messager say: N_STORYTELLER V_DOIT C_BEDTIME 0 self)
			)
			(14
				(ego solvePuzzle: fHearStory 2)
				(Bset fAfterStory)
				(curRoom newRoom: 430)
			)
		)
	)
)

(instance startContest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 420 hold:)
				(InitCrowd)
				(ego
					changeGait: MOVE_RUN
					ignoreActors: TRUE
					setCycle: StopWalk 5
					x: 187
					y: 117
				)
				((ScriptID YESUFU_TALKER 1)
					view: 983
					heading: 180
					loop: 2
					cel: 4
					x: 208
					y: 117
					setScale: 350
					init:
				)
				(= cycles 1)
			)
			(1
				(ego setHeading: 180)
				(= cycles 10)
			)
			(2
				(messager say: N_LAIBON V_DOIT C_START_CONTEST 0 self)
			)
			(3
				(ego setMotion: PolyPath 187 220 self)
				((ScriptID YESUFU_TALKER 1)
					origStep: 2053
					setCycle: StopWalk -1
					setMotion: PolyPath 208 220
				)
			)
			(4 (curRoom newRoom: 500))
		)
	)
)

(instance afterContest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 420 hold:)
				(Bset fAfterRace)
				(InitCrowd)
				(= gGOwnerMoveSpeed (ego moveSpeed?))
				(ego
					view: 1
					changeGait: MOVE_RUN
					setCycle: StopWalk 5
					setSpeed: 6
					x: 190
					y: 220
					ignoreActors: TRUE
					setHeading: 0
					setMotion: PolyPath 190 130 self
				)
				((ScriptID YESUFU_TALKER 1)
					view: 983
					x: 220
					y: 220
					origStep: 2053
					setScale: 350
					setCycle: StopWalk -1
					init:
					setMotion: PolyPath 220 130
				)
			)
			(1
				(= cycles 15)
			)
			(2
				(messager say: N_LAIBON V_DOIT C_AFTER_CONTEST 0 self)
			)
			(3
				(if wonBridgeWrestling
					(ego
						setMotion: PolyPath 177 117 self
						solvePuzzle: fWinInitiation 10 (| puzzleFIGHTER puzzlePALADIN)
					)
				else
					(ego solvePuzzle: fLoseInitiation 5 (| puzzleFIGHTER puzzlePALADIN))
					((ScriptID YESUFU_TALKER 1) setMotion: PolyPath 177 117 self)
				)
			)
			(4
				(if wonBridgeWrestling
					(ego setHeading: 270)
				else
					((ScriptID YESUFU_TALKER 1) setHeading: 270)
				)
				(= cycles 15)
			)
			(5
				(messager say: N_LAIBON V_DOIT C_STEP_UP 0 self)
			)
			(6
				(if wonBridgeWrestling
					(ego view: 13 loop: 0 cel: 0 setCycle: EndLoop self)
				else
					((ScriptID YESUFU_TALKER 1)
						view: 427
						loop: 0
						cel: 0
						setCycle: EndLoop self
					)
				)
			)
			(7
				(ego setSpeed: gGOwnerMoveSpeed)
				(messager say: N_LAIBON V_DOIT C_READY_MATCH 0 self)
			)
			(8
				(curRoom newRoom: 460)
			)
		)
	)
)

(instance afterMatch of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 420 hold:)
				(Bset fInitiationComplete)
				(InitCrowd)
				(ego setHeading: 180 x: 152 y: 114)
				((ScriptID YESUFU_TALKER 1)
					setHeading: 180
					setScale: 350
					x: 194
					y: 114
					init:
				)
				((ScriptID LAIBON_TALKER 1)
					setLoop: (if wonBridgeWrestling 3 else 2)
					x: 171
					y: 118
				)
				(= cycles 1)
			)
			(1
				(= cycles 15)
			)
			(2
				(messager say: N_LAIBON V_DOIT C_AFTER_MATCH 0 self)
			)
			(3
				(messager say: N_LAIBON V_DOIT C_CLOSING_SPEECH 0 self)
			)
			(4
				(if wonBridgeWrestling
					(messager say: N_LAIBON V_DOIT C_EGO_WINS 0 self)
				else
					(messager say: N_LAIBON V_DOIT C_YESUFU_WINS 0 self)
				)
			)
			(5
				((ScriptID LAIBON_TALKER 1) cycleSpeed: 6 setCycle: EndLoop self)
			)
			(6
				(if wonBridgeWrestling
					(ego view: 13 setCycle: EndLoop self)
				else
					((ScriptID YESUFU_TALKER 1) view: 427 setCycle: EndLoop self)
				)
			)
			(7
				(curRoom newRoom: 450)
			)
		)
	)
)

(instance returnSpear of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound changeTo: 420 hold:)
				(curRoom vanishingY: -80)
				(ego
					solvePuzzle: fReturnSpear 20
					addHonor: 50
					drop: iMagicSpear
				)
				(crowd loop: 2 x: 146 y: 189 init: addToPic:)
				((ScriptID LAIBON_TALKER 1)
					view: 454
					loop: 1
					cel: 0
					setScale: 300
					x: 22
					y: 142
					ignoreActors: TRUE
					init:
				)
				((ScriptID UHURA_TALKER 1)
					view: 970
					loop: 4
					cel: 1
					x: 67
					y: 132
					setScale: 300
					init:
				)
				((ScriptID RAKEESH_TALKER 1)
					view: 432
					loop: 2
					cel: 0
					setScale: 300
					x: 165
					y: 119
					init:
					addToPic:
				)
				(simba x: 141 y: 120 setScale: 300 init: addToPic:)
				((ScriptID STORY_TALKER 1)
					loop: 3
					cel: 0
					setScale: 300
					x: 130
					y: 117
					init:
					addToPic:
				)
				(ego
					changeGait: MOVE_WALK
					setCycle: StopWalk 5
					setHeading: 0
					setScale: 300
					x: 46
					y: 168
				)
				((ScriptID YESUFU_TALKER 1)
					view: 989
					loop: 0
					cel: 0
					x: 9
					y: 165
					setScale: 300
					ignoreActors: TRUE
					init:
				)
				(extra setScale: scaleX: 69 scaleY: 69 init: addToPic:)
				(extra2 setScale: scaleX: 69 scaleY: 69 init: addToPic:)
				(= cycles 1)
			)
			(1
				(= ticks 180)
			)
			(2
				(messager say: N_LAIBON V_DOIT C_PEACE 0 self)
			)
			(3
				(ego setMotion: PolyPath 52 139 self)
			)
			(4
				(ego view: 32 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(5
				(ego
					view: 0
					setCycle: StopWalk 5
					setMotion: MoveTo 30 142 self
				)
			)
			(6
				(= ticks 30)
			)
			(7
				(ego
					setLoop: 1
					setCycle: Reverse
					setMotion: MoveTo 52 142 self
				)
			)
			(8
				(ego view: 5 setCycle: 0 setLoop: 1)
				((ScriptID LAIBON_TALKER 1) loop: 5 setCycle: EndLoop self)
			)
			(9
				(= ticks 30)
			)
			(10
				(messager say: N_LAIBON V_DOIT C_SPEAR_RETURNED 0 self)
			)
			(11
				(if
					(or
						(== heroType MAGIC_USER)
						(and (not (Btst fStoleDrum)) (== heroType THIEF))
					)
					(ego get: iMagicDrum)
					(messager say: N_LAIBON V_DOIT C_GET_DRUM 0 self)
				else
					(= cycles 1)
				)
			)
			(12
				(= ticks 60)
			)
			(13
				(cond 
					((not (Btst fGaveDrum))
						(curRoom setScript: goTo620)
					)
					(
						(and
							(== heroType FIGHTER)
							(>= [egoStats HONOR] 150)
							(not (Btst fCantBePaladin))
						)
						(client setScript: goTo280)
					)
					((ego has: iGem)
						(curRoom setScript: goTo350)
					)
					(else
						(client setScript: goTo340)
					)
				)
			)
		)
	)
)

(instance showHole of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: 926 setLoop: 1 play: 127)
				(messager say: N_DIG_HOLE V_FINE_DAGGER C_DIG_HOLE 0 self)
			)
			(1
				((hutHole insetView?) setCel: 1)
				(messager say: N_CUE V_DOIT C_CAN_ENTER_HOLE 0 self)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance campfire of Feature
	(properties
		x 116
		y 145
		noun N_CAMPFIRE
		nsTop 134
		nsLeft 88
		nsBottom 157
		nsRight 145
		sightAngle 180
	)
)

(instance tree of Feature
	(properties
		x 235
		y 33
		noun N_TREE
		nsTop 16
		nsLeft 202
		nsBottom 51
		nsRight 268
		sightAngle 180
	)
)

(instance fence of Feature
	(properties
		x 159
		y 52
		noun N_FENCE
		nsTop 47
		nsBottom 58
		nsRight 319
		sightAngle 180
	)
)

(instance mountain of Feature
	(properties
		x 159
		y 31
		noun N_MOUNTAINS
		nsTop 26
		nsBottom 37
		nsRight 319
		sightAngle 180
	)
)

(instance guestHut of Feature
	(properties
		x 72
		y 92
		nsTop 81
		nsLeft 68
		nsBottom 103
		nsRight 77
		sightAngle 40
		approachX 58
		approachY 106
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(guestHutLook doVerb: V_LOOK &rest)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance leaderDoor of Feature
	(properties
		x 160
		y 54
		sightAngle 40
		onMeCheck cGREEN
		approachX 120
		approachY 84
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(chiefHut doVerb: V_LOOK &rest)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance chiefHut of Feature
	(properties
		x 162
		y 68
		noun N_LAIBON_HUT
		nsTop 54
		nsLeft 107
		nsBottom 82
		nsRight 218
		sightAngle 40
		approachX 225
		approachY 81
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((or (curRoom script?) (!= origHeroType THIEF))
						(super doVerb: V_LOOK &rest)
					)
					((Btst fLaibonHutRepaired)
						(messager say: N_LAIBON_HUT V_LOOK C_HUT_REPAIRED)
					)
					(else
						(messager say: N_LAIBON_HUT V_LOOK C_SEARCH_HUT 0)
						(curRoom setInset: hutHole)
					)
				)
			)
			(else 
				(super doVerb: theVerb)
			)
		)
	)
)

(instance uhuraHutLook of Feature
	(properties
		x 283
		y 92
		noun N_UHURA_HUT
		nsTop 78
		nsLeft 248
		nsBottom 107
		nsRight 318
		sightAngle 40
		approachX 282
		approachY 111
	)
)

(instance guestHutLook of Feature
	(properties
		x 47
		y 87
		noun N_GUEST_HUT
		nsTop 74
		nsLeft 2
		nsBottom 100
		nsRight 93
		sightAngle 40
		approachX 58
		approachY 106
	)
)

(instance crowd of View
	(properties
		x 146
		y 190
		view 420
		loop 1
		signal ignrAct
	)
)

(instance fire of Prop
	(properties
		x 117
		y 147
		noun N_CAMPFIRE
		view 420
		priority 11
		signal fixPriOn
	)
)

(instance simba of Actor
	(properties
		view 425
	)
)

(instance guard of Actor
	(properties
		x 140
		y 85
		noun N_GUARD
		view 482
		signal ignrAct
	)
)

(instance extra of Actor
	(properties
		x 191
		y 122
		view 207
		loop 5
		cel 3
	)
)

(instance extra2 of Actor
	(properties
		x 209
		y 132
		view 205
		loop 5
		cel 1
	)
)

(instance simbaCage of Cage
	(properties
		top 140
		left 240
		bottom 170
		right 280
	)
)

(instance hutHole of Inset
	(properties
		view 420
		loop 4
		x 200
		y 85
		disposeNotOnMe TRUE
		noun N_HUT_HOLE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DAGGER
				(if (not Night)
					(messager say: N_DIG_HOLE V_FINE_DAGGER C_CANT_DIG_DAY)
				else
					(self setScript: showHole)
				)
			)
			(V_FINE_DAGGER
				(self doVerb: V_DAGGER)
			)
			(V_WALK
				(if (== ((self insetView?) cel?) 1)
					(Bset fLaibonHutRepaired)
					(if (not (Btst fBreakIntoLaibonHut))
						(ego addHonor: -50)
					)
					(ego solvePuzzle: fBreakIntoLaibonHut 8 puzzleTHIEF)
					(self dispose:)
					(curRoom newRoom: 450)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_LOOK
				(if (== ((self insetView?) cel?) 1)
					(messager say: N_HUT_HOLE V_LOOK C_CAN_ENTER_HOLE)
				else
					(messager say: N_HUT_HOLE V_LOOK C_FIND_HOLE)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lionGroop of GradualLooper

	(method (doit)
		(cond 
			((not ((ScriptID RAKEESH_TALKER 1) mover?)) ((ScriptID RAKEESH_TALKER 1) view: 962))
			(
				(and
					(< 15 ((ScriptID RAKEESH_TALKER 1) heading?))
					(< ((ScriptID RAKEESH_TALKER 1) heading?) 75)
				)
				((ScriptID RAKEESH_TALKER 1) view: 967)
			)
			(
				(and
					(< 105 ((ScriptID RAKEESH_TALKER 1) heading?))
					(< ((ScriptID RAKEESH_TALKER 1) heading?) 165)
				)
				((ScriptID RAKEESH_TALKER 1) view: 967)
			)
			(
				(and
					(< 195 ((ScriptID RAKEESH_TALKER 1) heading?))
					(< ((ScriptID RAKEESH_TALKER 1) heading?) 255)
				)
				((ScriptID RAKEESH_TALKER 1) view: 967)
			)
			(
				(and
					(< 285 ((ScriptID RAKEESH_TALKER 1) heading?))
					(< ((ScriptID RAKEESH_TALKER 1) heading?) 345)
				)
				((ScriptID RAKEESH_TALKER 1) view: 967)
			)
		)
		(super doit: &rest)
	)
)

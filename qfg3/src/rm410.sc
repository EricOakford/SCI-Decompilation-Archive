;;; Sierra Script 1.0 - (do not remove this comment)
(script# 410)
(include game.sh) (include "410.shm")
(use Main)
(use TellerIcon)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Game)
(use User)
(use System)

(public
	rm410 0
)

(local
	local0 = [0 -10 -13 -15 999]
	local5 = [0 59 60 58 57 999]
	local11 = [0 13 -10 -14 14 15 999]
	local18 = [0 16 12 999]
	local22 = [0 17 18 999]
	local26 = [0 12 -19 999]
	local30 = [0 20 999]
	[local33 7]
	[local40 2]
	[local42 2]
	local44 = [0 -10 -14 -19 999]
	local49 = [0 -1 27 10 37 999]
	local55 = [0 21 3 -4 999]
	local60 = [0 5 6 -7 999]
	local65 = [0 8 999]
	local68 = [0 -1 -4 -7 999]
	local73 = [0 -1 27 28 26 999]
	local79 = [0 -21 52 999]
	local83 = [0 -23 -24 999]
	local87 = [0 22 999]
	local90 = [0 25 999]
	local93 = [0 -1 -21 -23 -24 999]
	local99 = [0 -30 34 32 38 999]
	local105 = [0 35 -39 999]
	local109 = [0 40 999]
	local112 = [0 -30 -39 999]
	local116 = [0 30 -34 -32 31 999]
	local122 = [0 35 36 999]
	local126 = [0 33 999]
	local129 = [0 -34 -32 999]
	local133 = [0 42 43 44 999]
	local138 = [0 42 -46 45 999]
	local143 = [0 47 999]
	local146 = [0 -46 999]
	local149 = [0 -13 51 52 53 999]
	local155 = [0 49 50 999]
	local159 = [0 -13 999]
	[local162 3]
	nightTime
	leopardmanCued
	dayLeopardMsg
	nightLeopardMsg
	dayDispelMsg
	nightDispelMsg
	dayCongratsMsg
	nightCongratsMsg
	roomConsolations
)
(procedure (ElderMsg whoCares)
	(cond 
		(dayCongratsMsg
			(messager say: N_ELDER_DAY V_DOIT C_AFTER_INITIATION 0 whoCares)
		)
		(nightCongratsMsg
			(messager say: N_ELDER_NIGHT V_DOIT C_AFTER_INITIATION 0 whoCares)
		)
		(dayDispelMsg
			(messager say: N_ELDER_DAY V_DOIT C_LEOPARD_DISPELLED 0 whoCares)
		)
		(nightDispelMsg
			(messager say: N_ELDER_NIGHT V_DOIT C_LEOPARD_DISPELLED 0 whoCares)
		)
		(nightLeopardMsg
			(messager say: N_ELDER_NIGHT V_DOIT C_PRISONER 0 whoCares)
		)
		(dayLeopardMsg
			(messager say: N_ELDER_DAY V_DOIT C_PRISONER 0 whoCares)
		)
		(nightTime
			(messager say: N_ELDER_NIGHT V_DOIT C_HELLO 0 whoCares)
		)
		(else
			(messager say: N_ELDER_DAY V_DOIT C_HELLO 0 whoCares)
		)
	)
)

(instance rm410 of Room
	(properties
		noun N_ROOM
		picture 410
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						237 189
						132 171
						90 148
						69 142
						69 153
						27 153
						27 129
						0 129
					yourself:
				)
		)
		(Load RES_MESSAGE 410)
		(= nightTime Night)
		(= [local40 0] @local5)
		(egoTell init: ego @local5 @local40)
		(leftTreeTop init:)
		(leftTreeTrunk init:)
		(rightTreeTop init:)
		(rightTreeTrunk init:)
		(rightTreeBot init:)
		(pens init:)
		(bridge init:)
		(village init:)
		(outCrop init:)
		(super init:)
		(if (not (Btst fCantEnterSimbani))
			((ScriptID ELDER_TALKER 1)
				noun: (if nightTime N_ELDER_NIGHT else N_ELDER_DAY)
				setScale:
				scaleX: 102
				scaleY: 102
				loop: 0
				x: 48
				y: 150
				init:
			)
			(if (Btst fLeopardmanCaptured)
				(= leopardmanCued TRUE)
			)
			(switch origHeroType
				(FIGHTER
					(if (and (not (Btst fLeopardmanCaptured)) (Btst fPracticedWithUhuraTwice))
						(= leopardmanCued TRUE)
					)
				)
				(MAGIC_USER
					(if (and (not (Btst fLeopardmanCaptured)) (or (ego has: iWood) [egoStats STAFF]))
						(= leopardmanCued TRUE)
					)
				)
				(THIEF
					(if (and (not (Btst fLeopardmanCaptured)) (Btst fBeenInLostCityPanorama))
						(= leopardmanCued TRUE)
					)
				)
			)
			(if (and leopardmanCued (not (Btst fNightElderAnnouncement)) nightTime)
				(Bset fNightElderAnnouncement)
				(= nightLeopardMsg TRUE)
			)
			(if (and leopardmanCued (not (Btst fDayElderAnnouncement)) (not nightTime))
				(Bset fDayElderAnnouncement)
				(= dayLeopardMsg TRUE)
			)
			(if (and (Btst fDispelledLeopardman) (not (Btst fDayDispelMsg)) (not nightTime))
				(Bset fDayDispelMsg)
				(= dayDispelMsg TRUE)
			)
			(if (and (Btst fDispelledLeopardman) (not (Btst fNightDispelMsg)) nightTime)
				(Bset fNightDispelMsg)
				(= nightDispelMsg TRUE)
			)
			(if (and wonBridgeWrestling (not (Btst fDayCongratsMsg)) (not nightTime))
				(Bset fDayCongratsMsg)
				(= dayCongratsMsg TRUE)
			)
			(if (and wonBridgeWrestling (not (Btst fNightCongratsMsg)) nightTime)
				(Bset fNightCongratsMsg)
				(= nightCongratsMsg TRUE)
			)
			(cond 
				(dayCongratsMsg
					(= [local33 0] @local133)
					(elderTell init: (ScriptID ELDER_TALKER 1) @local133 @local33)
				)
				(nightCongratsMsg
					(= [local33 0] @local138)
					(= [local33 1] @local143)
					(elderTell
						init: (ScriptID ELDER_TALKER 1) @local138 @local33 @local146
					)
				)
				(dayDispelMsg
					(= [local33 0] @local99)
					(= [local33 1] @local105)
					(= [local33 2] @local109)
					(elderTell
						init: (ScriptID ELDER_TALKER 1) @local99 @local33 @local112
					)
				)
				(nightDispelMsg
					(= [local33 0] @local116)
					(= [local33 1] @local122)
					(= [local33 2] @local126)
					(elderTell
						init: (ScriptID ELDER_TALKER 1) @local116 @local33 @local129
					)
				)
				(dayLeopardMsg
					(= [local33 0] @local49)
					(= [local33 1] @local55)
					(= [local33 2] @local60)
					(= [local33 3] @local65)
					(elderTell
						init: (ScriptID ELDER_TALKER 1) @local49 @local33 @local68
					)
				)
				(nightLeopardMsg
					(= [local33 0] @local73)
					(= [local33 1] @local79)
					(= [local33 2] @local83)
					(= [local33 3] @local87)
					(= [local33 4] @local90)
					(elderTell
						init: (ScriptID ELDER_TALKER 1) @local73 @local33 @local93
					)
				)
				(nightTime
					(= [local33 0] @local11)
					(= [local33 1] @local22)
					(= [local33 2] @local26)
					(= [local33 3] @local30)
					(elderTell
						init: (ScriptID ELDER_TALKER 1) @local11 @local33 @local44
					)
				)
				(else
					(= [local33 0] @local11)
					(= [local33 1] @local18)
					(elderTell
						init: (ScriptID ELDER_TALKER 1) @local11 @local33 @local44
					)
				)
			)
			(if nightTime
				((ScriptID ELDER_TALKER 0) view: 416)
				((ScriptID ELDER_TALKER 2) view: 416 nsLeft: 45 nsTop: 38)
				((ScriptID ELDER_TALKER 4) view: 416 nsLeft: 41 nsTop: 30)
				((ScriptID ELDER_TALKER 3) view: 416 nsLeft: 45 nsTop: 38)
			)
		)
		(if (== prevRoomNum 420)
			(ego
				normalize:
				noun: N_EGO_TELL
				x: 120
				y: 200
				setScale:
				scaleX: 102
				scaleY: 102
				init:
			)
		else
			(ego
				normalize:
				noun: N_EGO_TELL
				x: -10
				y: 180
				setScale:
				scaleX: 102
				scaleY: 102
				init:
			)
		)
		(if (not (Btst fFirstEnteredSimbaniOverlook))
			(= [local42 0] @local0)
			(rakeeshTell init: (ScriptID RAKEESH_TALKER 1) @local0 @local42)
			((ScriptID RAKEESH_TALKER 1)
				x: -10
				y: 160
				setScale:
				scaleX: 102
				scaleY: 102
				noun: 5
				actions: rakeeshTell
				init:
				setCycle: StopWalk 962
				setMotion: PolyPath 15 160
			)
		)
		(if (!= (cSound number?) 160)
			(cSound number: 160 setLoop: -1 play: 127)
		)
		(cSound hold: 0)
		(walkHandler addToFront: village)
		(cond 
			((== prevRoomNum 420)
				(self setScript: enterFromVillage)
			)
			((Btst fCantEnterSimbani)
				(self setScript: postConf)
			)
			((not (Btst fFirstEnteredSimbaniOverlook))
				(self setScript: enterWRakeesh)
			)
			(else
				(self setScript: enterRoom)
			)
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((<= (ego x?) 10)
				(self setScript: exitToMap)
			)
			((>= (ego y?) 178)
				(if (Btst fCantEnterSimbani)
					(self setScript: subHonor)
				else
					(self setScript: walkOut)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(walkHandler delete: curRoom)
		(walkHandler delete: village)
		(LoadMany FALSE RAKEESH_TALKER UHURA_TALKER ELDER_TALKER)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(egoTell doVerb: theVerb)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoTell of Teller
	
	(method (showDialog)
		(super
			showDialog:
				59
				(if (and (Btst fFirstEnteredSimbaniOverlook) (not nightTime))
					(not (Btst fCantEnterSimbani))
				else
					0
				)
				58
				(if (and (Btst fFirstEnteredSimbaniOverlook) (not nightTime))
					(not (Btst fCantEnterSimbani))
				else
					0
				)
				60 (if nightTime (not (Btst fCantEnterSimbani)) else 0)
				57 (if nightTime (not (Btst fCantEnterSimbani)) else 0)
				-61 (not (Btst fFirstEnteredSimbaniOverlook))
				-62 (not (Btst fFirstEnteredSimbaniOverlook))
				55 (Btst fCantEnterSimbani)
				56 (Btst fCantEnterSimbani)
				-54 (Btst fCantEnterSimbani)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-54
					(if (not roomConsolations)
						(++ roomConsolations)
						(ego addHonor: 30)
					)
				)
				(-62
					(messager say: N_EGO_TELL V_TELL C_FIRST_GOODBYE)
					(curRoom setScript: goToVillage)
					(return FALSE)
				)
				(-61
					(messager say: N_EGO_TELL V_TELL C_FIRST_HELLO)
					(curRoom setScript: goToVillage)
					(return FALSE)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(messager say: N_RAKEESH V_DOIT C_WAIT_A_SECOND)
				(curRoom setScript: goToVillage)
				((User curEvent?) claimed: TRUE)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance elderTell of Teller
	
	(method (showDialog)
		(super
			showDialog:
				-14 nightTime
				C_SAVANNA (not nightTime)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(if (not (Btst fFirstEnteredSimbaniOverlook))
					(curRoom setScript: goToVillage)
					(super doVerb: theVerb &rest)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_WALK
				((User curEvent?) claimed: TRUE)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rakeeshTell of Teller

	(method (doChild)
		(return
			(switch query
				(-10
					(messager say: N_RAKEESH V_TELL C_SIMBANI)
					(curRoom setScript: goToVillage)
					(return FALSE)
				)
				(-13
					(messager say: N_RAKEESH V_TELL C_VILLAGE)
					(curRoom setScript: goToVillage)
					(return FALSE)
				)
				(-15
					(messager say: N_RAKEESH V_TELL C_ELDER)
					(curRoom setScript: goToVillage)
					(return FALSE)
				)
			)
		)
	)
)

(instance uhuraTell of Teller)

(instance goToVillage of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID ELDER_TALKER 1) loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(1
				(messager say: N_ELDER_DAY V_DOIT C_VILLAGE 0 self)
			)
			(2
				(ego setMotion: PolyPath 129 200 self)
				((ScriptID RAKEESH_TALKER 1) setMotion: PolyPath 125 190)
			)
			(3
				(cast eachElementDo: #hide)
				(curRoom drawPic: 0 PIXELDISSOLVE)
				(= cycles 12)
			)
			(4
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance enterWRakeesh of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fFirstEnteredSimbaniOverlook)
				(ego setMotion: PolyPath 55 170 self)
			)
			(1
				(Face ego (ScriptID ELDER_TALKER 1) self)
			)
			(2
				((ScriptID ELDER_TALKER 1) setCycle: EndLoop self)
			)
			(3
				((ScriptID ELDER_TALKER 1) cel: 0 loop: 1 setCycle: EndLoop self)
			)
			(4
				(messager say: N_RAKEESH V_DOIT C_HELLO 0 self)
			)
			(5
				(HandsOn)
				(walkHandler addToFront: curRoom)
				(= seconds 5)
			)
			(6
				(walkHandler delete: curRoom)
				(client setScript: goToVillage self)
			)
		)
	)
)

(instance exitToMap of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (- (ego x?) 15) (ego y?) self)
			)
			(1
				(curRoom newRoom: 160)
			)
		)
	)
)

(instance enterFromVillage of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 90 165 self)
			)
			(1
				((ScriptID ELDER_TALKER 1) setCycle: EndLoop self)
			)
			(2
				((ScriptID ELDER_TALKER 1) cel: 0 loop: 1 setCycle: EndLoop self)
			)
			(3
				(ElderMsg self)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance walkOut of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 129 200 self)
			)
			(1
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance enterRoom of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 65 160 self)
			)
			(1
				(ego setHeading: 345)
				(= cycles 18)
			)
			(2
				((ScriptID ELDER_TALKER 1) setCycle: EndLoop self)
			)
			(3
				((ScriptID ELDER_TALKER 1) cel: 0 loop: 1 setCycle: EndLoop self)
			)
			(4
				(ElderMsg self)
			)
			(5
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance postConf of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= [local162 0] @local149)
				(= [local162 1] @local155)
				(uhuraTell
					init: (ScriptID UHURA_TALKER 1) @local149 @local162 @local159
				)
				((ScriptID UHURA_TALKER 1)
					setScale: 220
					x: 90
					y: 160
					setHeading: 270
					noun: N_UHURA
					init:
				)
				(ego setMotion: PolyPath 50 150 self)
			)
			(1
				(messager say: N_UHURA V_DOIT C_HELLO)
				(= cycles 1)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance subHonor of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(messager say: N_UHURA V_DOIT C_AFTER_CONFERENCE)
			)
			(1
				(ego
					setMotion: MoveTo (ego x?) (+ (ego y?) 10) self
					addHonor: -20
				)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance leftTreeTop of Feature
	(properties
		x 58
		y 28
		noun N_LEFT_TREETOP
		nsTop -2
		nsBottom 59
		nsRight 117
		sightAngle 180
	)
)

(instance leftTreeTrunk of Feature
	(properties
		x 20
		y 93
		noun N_LEFT_TREETRUNK
		nsTop 57
		nsBottom 129
		nsRight 41
		sightAngle 180
	)
)

(instance rightTreeTop of Feature
	(properties
		x 261
		y 29
		noun N_RIGHT_TREETOP
		nsLeft 203
		nsBottom 58
		nsRight 319
		sightAngle 180
	)
)

(instance rightTreeTrunk of Feature
	(properties
		x 302
		y 124
		noun N_RIGHT_TREETRUNK
		nsTop 59
		nsLeft 285
		nsBottom 189
		nsRight 319
		sightAngle 180
	)
)

(instance rightTreeBot of Feature
	(properties
		x 236
		y 168
		noun N_RIGHT_TREE_BOTTOM
		nsTop 148
		nsLeft 186
		nsBottom 189
		nsRight 286
		sightAngle 180
	)
)

(instance pens of Feature
	(properties
		x 77
		y 70
		noun N_PENS
		nsTop 60
		nsLeft 33
		nsBottom 81
		nsRight 122
		sightAngle 180
	)
)

(instance bridge of Feature
	(properties
		x 269
		y 93
		noun N_BRIDGE
		nsTop 83
		nsLeft 256
		nsBottom 104
		nsRight 283
		sightAngle 180
	)
)

(instance village of Feature
	(properties
		x 163
		y 59
		noun N_VILLAGE
		nsTop 58
		nsLeft 24
		nsBottom 134
		nsRight 303
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(cond 
					((walkHandler contains: curRoom)
						(super doVerb: V_WALK)
					)
					((not (curRoom script?))
						(curRoom setScript: walkOut)
						((User curEvent?) claimed: TRUE)
					)
					(else
						(super doVerb: V_WALK)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance outCrop of Feature
	(properties
		x 117
		y 129
		noun N_OUTCROP
		nsTop 128
		nsBottom 189
		nsRight 234
		sightAngle 180
	)
)

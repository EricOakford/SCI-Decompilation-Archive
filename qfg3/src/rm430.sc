;;; Sierra Script 1.0 - (do not remove this comment)
(script# 430)
(include game.sh) (include "430.shm")
(use Main)
(use TellerIcon)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm430 0
)

(local
	egoSitting
	local1
	local2
	local3
	thisTime
	evtX =  160
	evtY =  130
	uhuraIsHere
	uhuraAsked
	local9
	uhuraLeft
	local11
	local12
	local13
	local14 = [0 50 -30 5 -53 -51 4 -31 -20 -15 -23 999]
	[local26 2]
	local28 = [0 -61 -54 -12 -58 999]
	local34 = [0 -3 -24 999]
	local38 = [0 -62 999]
	local41 = [0 2 999]
	local44 = [0 -60 -7 999]
	local48 = [0 6 999]
	local51 = [0 -56 999]
	local54 = [0 9 999]
	local57 = [0 -16 999]
	local60 = [0 -17 999]
	local63 = [0 -59 -18 999]
	local67 = [0 -21 999]
	local70 = [0 19 999]
	local73 = [0 22 999]
	local76 = [0 -57 999]
	local79 = [0 14 999]
	local82 = [0 -26 29 -25 999]
	local87 = [0 -27 999]
	local90 = [0 28 999]
	local93 = [0 -61 -54 -12 -58 -3 -24 -62 -60 -7 -56 -16 -17 -59 -18 -21 -57 -26 -27 999]
	[local113 20]
	local133 = [0 -34 -3 -37 -38 -19 999]
	local140 = [0 -35 -6 999]
	local144 = [0 -36 999]
	local147 = [0 -34 -6 999]
	[local151 4]
)
(procedure (localproc_1ad8 param1)
	(return (* 2 (/ (+ (* param1 800) (- local3 1)) local3)))
)

(procedure (NobodyHere)
	(return
		(if
			(and
				(not (cast contains: (ScriptID RAKEESH_TALKER 1)))
				(not uhuraIsHere)
			)
			(not local9)
		else
			0
		)
	)
)

(instance rm430 of Room
	(properties
		noun N_ROOM
		picture 430
		vanishingY -300
	)
	
	(method (init)
		(HandsOff)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						263 117
						219 117
						172 106
						175 98
						141 98
						131 91
						64 91
						64 153
						101 139
						116 128
						168 128
						199 128
						199 165
						183 189
						263 189
					yourself:
				)
		)
		(mat approachVerbs: V_DO init:)
		(cSound setLoop: -1 changeTo: 430)
		(soundFx number: 913 setLoop: -1 play:)
		(fire setCycle: Forward init:)
		(shield init:)
		(zebra init:)
		(leftbed init:)
		(rightbed init:)
		(wall init:)
		(fireplace init:)
		(basket init:)
		(log init:)
		(= [local26 0] @local14)
		(egoActions init: ego @local14 @local26)
		(if (not (== prevRoomNum 440))
			(walkHandler addToFront: curRoom)
		)
		(cond 
			((== prevRoomNum 440)
				(curRoom setScript: firstTime)
			)
			((Btst fAfterStory)
				(self style: DISSOLVE)
				(curRoom setScript: afterStory)
			)
			(else
				(curRoom setScript: enterRoom)
			)
		)
		(chestLid approachVerbs: V_DO 58 init: stopUpd:)
		(chest approachVerbs: V_DO 58 init:)
		(super init:)
	)
	
	(method (doit)
		(cond 
			((and (GameIsRestarting) (== (ego view?) 40))
				(= egoSitting 1)
			)
			((curRoom script?) 0)
			((and (ego mover?) (== (ego view?) 40))
				(if (IsObject (ego looper?))
					((ego looper?) dispose:)
				)
				(ego setMotion: 0 setScript: egoGetUp)
			)
			((and (> (ego y?) 155) (> (ego x?) 170))
				(curRoom setScript: exitRoom)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(globalSound stop:)
		(soundFx stop:)
		(walkHandler delete: curRoom)
		(LoadMany FALSE CHEST UHURA_TALKER RAKEESH_TALKER)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_SLEEP
					(if (cast contains: (ScriptID UHURA_TALKER 1))
						(self setScript: uhuraGoodNight 0 0)
					else
						(self setScript: goToBed)
					)
				)
				(V_WALK
					(egoActions doVerb: V_WALK)
				)
				(V_LEVITATE
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: LEVITATE)
							((ScriptID LEVITATION 0) init: (ego x?) (+ (ego y?) 1) 20)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(82
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: 26)
							(self setScript: (ScriptID 37 0))
							(return TRUE)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_FLAME
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: FLAMEDART)
							(self setScript: (ScriptID PROJECTILE 0) self V_FLAME)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_FORCEBOLT
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: FORCEBOLT)
							(self setScript: (ScriptID PROJECTILE 0) self V_FORCEBOLT)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_OPEN
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: OPEN)
							(FindTarget
								((User curEvent?) x?)
								((User curEvent?) y?)
							)
							(ego setScript: (ScriptID CASTOPEN) 0 chest)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_LIGHTNING
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: LIGHTNING)
							(self setScript: (ScriptID PROJECTILE 0) self V_LIGHTNING)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_CALM
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: CALM)
							(ego setScript: (ScriptID CASTAREA 0) 0 V_CALM)
							(super doVerb: V_CALM)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_JUGGLE
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: JUGGLE)
							(ego setScript: (ScriptID CASTJUGGLE 0))
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_DAZZLE
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: DAZZLE)
							(ego setScript: (ScriptID CASTAREA 0) 0 V_DAZZLE)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_DETECT
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: DETMAGIC)
							(ego setScript: (ScriptID CASTAREA 0) 0 V_DETECT)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_TRIGGER
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: TRIGGER)
							(ego setScript: (ScriptID CASTAREA 0) 0 V_TRIGGER)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_REVERSAL
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: REVERSAL)
							(self setScript: (ScriptID CASTAREA 0) 0 V_REVERSAL)
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(V_STAFF
					(if (NobodyHere)
						(ego addHonor: -5)
						(if (ego castSpell: STAFF)
							(ego setScript: (ScriptID STAFF_SCRIPT 0))
						)
					else
						(messager say: N_CUE V_DOIT C_NO_MAGIC 0 0)
					)
				)
				(-77
					(messager say: NULL NULL 2 1 0 CASTAREA)
				)
				(-76
					(messager say: NULL NULL 1 1 0 CASTAREA)
				)
				(-80
					(messager say: NULL NULL 4 1 0 CASTAREA)
				)
				(else
					(super doVerb: theVerb)
				)
			)
		)
	)
)

(instance egoActions of Teller
	
	(method (showDialog)
		(super
			showDialog:
				50 (cast contains: (ScriptID RAKEESH_TALKER 1))
				-30 (cast contains: (ScriptID RAKEESH_TALKER 1))
				5 (cast contains: (ScriptID RAKEESH_TALKER 1))
				-53 (if (not uhuraAsked) uhuraIsHere else 0)
				-51 (if (not uhuraAsked) uhuraIsHere else 0)
				4 (if (and (not local12) uhuraIsHere) else local9)
				-31 (if (and (not local12) uhuraIsHere) else local9)
				-20 (NobodyHere)
				-15 (if Night (NobodyHere) else 0)
				-23 (NobodyHere)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-53
					(= uhuraAsked 1)
					(HandsOff)
					(messager say: N_EGO_TELL V_TELL C_YES 0)
					(curRoom setScript: walkUhuraIn)
					(return 0)
				)
				(-51
					(= uhuraAsked 1)
					(= local12 1)
					(return TRUE)
				)
				(-31
					(= uhuraAsked 1)
					(messager say: N_EGO_TELL V_TELL C_GOODNIGHT_UHURA)
					(curRoom setScript: uhuraGoodNight 0 1)
					(return 0)
				)
				(-20
					(= query C_TALK_NOBODY)
					(return TRUE)
				)
				(-15
					(= query C_TALK_NOBODY)
					(return TRUE)
				)
				(-23
					(= query C_TALK_NOBODY)
					(return TRUE)
				)
				(-30
					(messager say: N_EGO_TELL V_TELL C_GOODNIGHT_RAKEESH)
					(curRoom doVerb: V_SLEEP)
					(return FALSE)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_WALK
					(cond 
						((curRoom script?) 0)
						((== (ego view?) 40)
							(= evtX ((User curEvent?) x?))
							(= evtY ((User curEvent?) y?))
							(curRoom setScript: egoGetUp)
							(return TRUE)
						)
					)
				)
				(V_TALK
					(super doVerb: theVerb)
				)
				(else
					(ego doVerb: theVerb)
				)
			)
		)
	)
)

(instance rakeeshTell of Teller

	(method (showDialog)
		(super
			showDialog:
			-24 (== origHeroType MAGIC_USER)
			C_MAGIC_ACCEPTED (== origHeroType MAGIC_USER)
		)
	)
	
	(method (doChild)
		(return
			(if (== query -25)
				(curRoom setScript: checkForMagic)
				(return FALSE)
			else
				(super doChild: &rest)
			)
		)
	)
)

(instance uhuraTell of Teller
	
	(method (doChild)
		(switch query
			(-34
				(|= local2 $0001)
				(super doChild: query)
			)
			(-3
				(|= local2 $0002)
				(return TRUE)
			)
			(-37
				(|= local2 $0004)
				(return TRUE)
			)
			(-38
				(|= local2 $0008)
				(return TRUE)
			)
			(-19
				(|= local2 $0010)
				(return TRUE)
			)
			(-35
				(|= local2 $0020)
				(return TRUE)
			)
			(-36
				(|= local2 $0040)
				(return TRUE)
			)
			(-6
				(|= local2 $0080)
				(super doChild: query)
			)
		)
		(return
			(= local11 TRUE)
		)
	)
)

(instance checkForMagic of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_RAKEESH V_TELL C_TRUST 1 self)
			)
			(1
				(if [egoStats MAGIC]
					(messager say: N_RAKEESH V_TELL C_TRUST 2 self)
				else
					(= cycles 1)
				)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance afterStory of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr fFlag31)
				(ego
					view: 0
					setLoop: 0
					x: 102
					y: 106
					setScale:
					scaleX: 156
					scaleY: 156
					signal: (| (ego signal?) skipCheck)
					init:
				)
				(= cycles 3)
			)
			(1
				(self setScript: goToBed self)
			)
			(2
				(Bclr fAfterStory)
				(self dispose:)
			)
		)
	)
)

(instance goToBed of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(if egoSitting
					(ego view: 35 loop: 0 setCel: 8 x: 102 y: 106)
					(self changeState: (+= state 2))
				else
					(ego setMotion: PolyPath 76 108 self)
				)
			)
			(1
				(ego
					view: 35
					loop: 0
					cel: 0
					x: 102
					y: 106
					setCycle: EndLoop self
				)
			)
			(2
				(= seconds 3)
			)
			(3
				(cast eachElementDo: #hide)
				(if (cast contains: (ScriptID RAKEESH_TALKER 1))
					((ScriptID RAKEESH_TALKER 1) dispose:)
				)
				(curRoom drawPic: 0 PIXELDISSOLVE)
				(= seconds 3)
			)
			(4
				(cast eachElementDo: #show)
				(curRoom drawPic: 430 PIXELDISSOLVE)
				(= cycles 3)
			)
			(5
				(UnLoad RES_PIC 0)
				((ScriptID TIME 7) init: 5 40)
				(if (PalVary PALVARYINFO)
					(PalVary PALVARYREVERSE 0)
					(Bclr fEgoIsAsleep)
				)
				(ego setCycle: BegLoop self)
			)
			(6
				(ego normalize: 6 cel: 6 x: 76 y: 108)
				(= cycles 3)
			)
			(7
				(if (and (== Day (+ rakeeshSimbaniDay 1)) (not (Btst fRakeeshLeftSimbani)))
					(Bset fRakeeshLeftSimbani)
					(messager say: N_ENTRANCE V_DOIT C_AWAKEN 0 self)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance checkTime of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= thisTime (GetTime))
				(= cycles 1)
			)
			(1
				(= cycles 5)
			)
			(2
				(= local3 (- (= temp0 (GetTime)) thisTime))
				(self dispose:)
			)
		)
	)
)

(instance uhuraGoodNight of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID UHURA_TALKER 1)
					setMotion:
						PolyPath
						((ScriptID UHURA_TALKER 1) x?)
						(+ ((ScriptID UHURA_TALKER 1) y?) 100)
						self
				)
			)
			(1
				(if register (HandsOn))
				(= uhuraIsHere FALSE)
				(= local12 1)
				(= local9 0)
				((ScriptID UHURA_TALKER 1) dispose:)
				(self dispose:)
			)
		)
	)
)

(instance uhuraLeave of Script
	
	(method (doit)
		(if local11
			(= local11 0)
			(self cycles: 200)
		)
		(if (== local2 255)
			(self changeState: 1)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_UHURA V_DOIT C_UHURA_LEAVE)
				(= uhuraLeft TRUE)
				(= cycles 200)
			)
			(1
				(HandsOff)
				(messager say: N_UHURA V_DOIT C_UHURA_GO 0 self)
			)
			(2
				((ScriptID UHURA_TALKER 1)
					setMotion:
						PolyPath
						((ScriptID UHURA_TALKER 1) x?)
						(+ ((ScriptID UHURA_TALKER 1) y?) 100)
						self
				)
			)
			(3
				(HandsOn)
				(= uhuraIsHere FALSE)
				(= local12 1)
				(= local9 0)
				((ScriptID UHURA_TALKER 1) dispose:)
				(self dispose:)
			)
		)
	)
)

(instance stopEgo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= uhuraAsked TRUE)
				(ego setMotion: 0)
				(messager say: N_ROOM V_WALK C_UHURA_ASKS 0 self)
			)
			(1
				(ego setMotion: PolyPath 190 135 self)
			)
			(2
				(ego setHeading: 180)
				(= cycles 18)
			)
			(3
				(client setScript: walkUhuraIn)
			)
		)
	)
)

(instance walkUhuraIn of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local9 1)
				(= [local151 0] @local133)
				(= [local151 1] @local140)
				(= [local151 2] @local144)
				(uhuraTell
					init: (ScriptID UHURA_TALKER 1) @local133 @local151 @local147
				)
				((ScriptID UHURA_TALKER 1)
					x: 200
					y: 250
					noun: 3
					setScale:
					scaleX: 156
					scaleY: 156
					init:
					setCycle: Walk
					setMotion: MoveTo 200 155 self
				)
			)
			(1
				((ScriptID UHURA_TALKER 1) setScript: uhuraLeave)
				(= cycles 1)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance exitRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						(Btst fLeopardmanCaptured)
						(not (Btst fDispelledLeopardman))
						(== timeODay TIME_NIGHT)
						(not egoSitting)
						(not local9)
						(not local12)
						(!= Day rakeeshSimbaniDay)
					)
					(client setScript: stopEgo)
				)
				(HandsOff)
				(if (and local9 (cast contains: (ScriptID UHURA_TALKER 1)))
					(ego setMotion: 0)
					(messager say: N_EGO_TELL V_WALK C_UHURA_TIME_UP)
					((ScriptID UHURA_TALKER 1)
						setMotion:
							PolyPath
							((ScriptID UHURA_TALKER 1) x?)
							(+ ((ScriptID UHURA_TALKER 1) y?) 50)
							self
					)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 30) self)
			)
			(2
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance uhuraTryEnter of Script
	(properties)
	
	(method (doit)
		(cond 
			(local9 (self dispose:))
			(uhuraAsked (= uhuraIsHere 0)
				(self dispose:)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= uhuraIsHere TRUE)
				(messager say: N_UHURA V_DOIT C_UHURA_ASKS 0 self)
			)
			(1
				(= cycles (localproc_1ad8 10))
			)
			(2
				(messager say: 3 6 52)
				(= cycles (localproc_1ad8 10))
			)
			(3
				(= uhuraIsHere FALSE)
				(= local12 1)
				(self dispose:)
			)
		)
	)
)

(instance egoGetUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: BegLoop self))
			(1
				(ego view: 0 normalize: setMotion: PolyPath evtX evtY)
				(= egoSitting 0)
				(walkHandler delete: curRoom)
				(self dispose:)
			)
		)
	)
)

(instance firstTime of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= rakeeshSimbaniDay Day)
				(= [local113 0] @local28)
				(= [local113 1] @local34)
				(= [local113 2] @local57)
				(= [local113 3] @local76)
				(= [local113 4] @local82)
				(= [local113 5] @local38)
				(= [local113 6] @local41)
				(= [local113 7] @local44)
				(= [local113 8] @local48)
				(= [local113 9] @local51)
				(= [local113 10] @local54)
				(= [local113 11] @local60)
				(= [local113 12] @local63)
				(= [local113 13] @local67)
				(= [local113 14] @local70)
				(= [local113 15] @local73)
				(= [local113 16] @local79)
				(= [local113 17] @local87)
				(= [local113 18] @local90)
				(rakeeshTell
					init: (ScriptID RAKEESH_TALKER 1) @local28 @local113 @local93
				)
				(= egoSitting 1)
				(curRoom drawPic: 0)
				(Animate 0)
				((ScriptID TIME 4) init: 20 0)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					view: 40
					setCycle: 0
					setLoop: -1
					setLoop: 0
					setMotion: 0
					cel: 5
					x: 103
					y: 105
					setScale:
					scaleX: 156
					scaleY: 156
					actions: egoActions
					noun: 2
					signal: (| (ego signal?) skipCheck)
					init:
				)
				((ScriptID RAKEESH_TALKER 1)
					view: 432
					loop: 2
					cel: 0
					x: 232
					y: 105
					noun: 1
					signal: (| ((ScriptID RAKEESH_TALKER 1) signal?) skipCheck)
					init:
				)
				(= seconds 1)
			)
			(1
				(if (PalVary PALVARYINFO)
					(PalVary PALVARYNEWTIME 1)
				)
				(messager say: N_RAKEESH V_DOIT C_FIRST_TIME 0 self)
			)
			(2
				(UnLoad RES_PIC 0)
				(switch origHeroType
					(FIGHTER
						(messager say: N_RAKEESH V_DOIT C_FIGHTER 0 self)
					)
					(MAGIC_USER
						(Bset 133)
						(messager say: N_RAKEESH V_DOIT C_MAGIC_USER 0 self)
					)
					(THIEF
						(messager say: N_RAKEESH V_DOIT C_THIEF 0 self)
					)
				)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sitDown of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 103 105 self)
			)
			(1
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego view: 40 setLoop: 0 setCycle: EndLoop self)
				(= egoSitting TRUE)
			)
			(2
				(if
					(and
						(Btst fLeopardmanCaptured)
						(not (Btst fDispelledLeopardman))
						(== timeODay TIME_NIGHT)
						(not uhuraIsHere)
						(not local12)
						(!= Day rakeeshSimbaniDay)
					)
					(ego setScript: uhuraTryEnter)
				)
				(= cycles 1)
			)
			(3
				(walkHandler addToFront: curRoom)
				(self dispose:)
			)
		)
	)
)

(instance enterRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (not (Btst fRakeeshLeftSimbani)) (== Day rakeeshSimbaniDay))
					(= [local113 0] @local28)
					(= [local113 1] @local34)
					(= [local113 2] @local57)
					(= [local113 3] @local76)
					(= [local113 4] @local82)
					(= [local113 5] @local38)
					(= [local113 6] @local41)
					(= [local113 7] @local44)
					(= [local113 8] @local48)
					(= [local113 9] @local51)
					(= [local113 10] @local54)
					(= [local113 11] @local60)
					(= [local113 12] @local63)
					(= [local113 13] @local67)
					(= [local113 14] @local70)
					(= [local113 15] @local73)
					(= [local113 16] @local79)
					(= [local113 17] @local87)
					(= [local113 18] @local90)
					(rakeeshTell
						init: (ScriptID RAKEESH_TALKER 1) @local28 @local113 @local93
					)
					((ScriptID 35 1)
						view: 432
						loop: 2
						cel: 0
						x: 232
						y: 105
						noun: 1
						signal: (| ((ScriptID RAKEESH_TALKER 1) signal?) skipCheck)
						init:
					)
				)
				(ego
					normalize:
					x: 240
					y: 180
					setScale:
					scaleX: 156
					scaleY: 156
					actions: egoActions
					noun: 2
					setMotion: PolyPath 240 150 self
					signal: (| (ego signal?) $1000)
					init:
				)
			)
			(1
				(cond 
					((and (== Day rakeeshSimbaniDay) (== prevRoomNum 420))
						(messager say: N_RAKEESH V_DOIT C_RAKEESH_STILL_HERE 0 self)
					)
					((and (== Day (+ rakeeshSimbaniDay 1)) (not (Btst fRakeeshLeftSimbani)))
						(Bset fRakeeshLeftSimbani)
						(messager say: N_ENTRANCE V_DOIT C_RAKEESH_LEFT 0 self)
					)
					((and (Btst fLeopardmanCaptured) (== timeODay TIME_NIGHT))
						(self setScript: checkTime self)
					)
					((and (!= Day simbaniFoodDay) (== timeODay TIME_NIGHT))
						(messager say: N_ENTRANCE V_DOIT C_FIND_FOOD 0 self)
						(++ freeMeals)	;EO: Now credits an eaten meal
					)
					(else
						(= ticks 1)
					)
				)
			)
			(2
				(= simbaniFoodDay Day)
				(HandsOn)
				(DisableIcons)
				(self dispose:)
			)
		)
	)
)

(instance walkToChest of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(self setScript: egoGetUp self)
			)
			(1
				(HandsOff)
				(ego setMotion: PolyPath 155 99 self)
			)
			(2
				(chest doVerb: register)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance useChest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(chestLid setLoop: 1)
				(globalSound number: 311 setLoop: 1 play:)
				(ego view: 4 setLoop: 0 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1 (= ticks 60))
			(2
				((ScriptID CHEST 0) init:)
				(= cycles 1)
			)
			(3 (ego setCycle: CycleTo 0 -1 self))
			(4
				(ego normalize:)
				(chestLid setLoop: 2 stopUpd:)
				(globalSound number: 312 setLoop: 1 play:)
				(= cycles 1)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance useChestMag of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(chestLid setLoop: 1)
				(globalSound number: 311 setLoop: 1 play:)
				(= cycles 6)
			)
			(1
				((ScriptID CHEST 0) init:)
				(= cycles 1)
			)
			(2
				(chestLid setLoop: 2 stopUpd:)
				(globalSound number: 312 setLoop: 1 play:)
				(= cycles 1)
			)
			(3
				(chestLid stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fillChest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(chestLid loop: 1)
				(globalSound number: 311 setLoop: 1 play:)
				(ego view: 4 setLoop: 0 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1
				(= ticks 60)
			)
			(2
				((ScriptID CHEST 1) init: local1)
				(= cycles 1)
			)
			(3
				(ego setCycle: CycleTo 0 -1 self)
			)
			(4
				(ego normalize:)
				(chestLid loop: 2)
				(globalSound number: 312 setLoop: 1 play:)
				(= cycles 1)
			)
			(5
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance warmHands of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if egoSitting
					(self setScript: egoGetUp self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOff)
				(ego setMotion: PolyPath 149 130 self)
			)
			(2
				(ego setHeading: 180)
				(= cycles 18)
			)
			(3
				(messager say: N_FIRE V_DO NULL 0 self)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance shield of Feature
	(properties
		x 86
		y 50
		noun N_SHIELD
		nsTop 26
		nsLeft 72
		nsBottom 74
		nsRight 100
		sightAngle 180
	)
)

(instance zebra of Feature
	(properties
		x 233
		y 50
		noun N_ZEBRA
		nsTop 24
		nsLeft 204
		nsBottom 76
		nsRight 263
		sightAngle 180
	)
)

(instance leftbed of Feature
	(properties
		x 95
		y 97
		noun N_LEFTBED
		nsTop 87
		nsLeft 45
		nsBottom 107
		nsRight 146
		sightAngle 180
	)
)

(instance rightbed of Feature
	(properties
		x 241
		y 100
		noun N_RIGHTBED
		nsTop 83
		nsLeft 198
		nsBottom 118
		nsRight 284
		sightAngle 180
	)
)

(instance wall of Feature
	(properties
		x 163
		y 26
		noun N_WALL
		nsTop 24
		nsLeft 38
		nsBottom 82
		nsRight 288
		sightAngle 180
	)
)

(instance fireplace of Feature
	(properties
		x 145
		y 136
		noun N_FIREPLACE
		nsTop 122
		nsLeft 108
		nsBottom 150
		nsRight 183
		sightAngle 180
	)
)

(instance basket of Feature
	(properties
		x 59
		y 146
		noun N_BASKET
		nsTop 136
		nsLeft 47
		nsBottom 157
		nsRight 71
		sightAngle 180
	)
)

(instance log of Feature
	(properties
		x 81
		y 145
		noun N_LOG
		nsTop 140
		nsLeft 73
		nsBottom 151
		nsRight 89
		sightAngle 180
	)
)

(instance chest of Feature
	(properties
		x 165
		y 82
		noun N_CHEST
		nsTop 74
		nsLeft 144
		nsBottom 95
		nsRight 192
		sightAngle 40
		approachX 155
		approachY 99
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb -75)
				(chestLid setScript: useChestMag)
			)
			((== theVerb V_DO)
				(if egoSitting
					(curRoom setScript: walkToChest 0 theVerb)
				else
					(chestLid setScript: useChest)
				)
			)
			((and (< V_HELP theVerb) (< theVerb 62))
				(if (>= theVerb V_PIN)
					(= local1 (- theVerb 11))
				else
					(= local1 (- theVerb 10))
				)
				(if egoSitting
					(curRoom setScript: walkToChest 0 theVerb)
				else
					(chestLid setScript: fillChest)
				)
			)
			(else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mat of Feature
	(properties
		x 95
		y 99
		noun N_MAT
		sightAngle 40
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					((ego script?) 0)
					((== (ego view?) 40) 0)
					(else (ego setScript: sitDown))
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance chestLid of Prop
	(properties
		x 161
		y 78
		view 430
		loop 2
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(chest doVerb: theVerb &rest)
	)
)

(instance fire of Prop
	(properties
		x 145
		y 141
		noun N_FIRE
		view 423
		priority 14
		signal fixPriOn
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(if (and (not (curRoom script?)) (== theVerb V_DO))
			(curRoom setScript: warmHands)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

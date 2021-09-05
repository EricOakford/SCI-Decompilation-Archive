;;; Sierra Script 1.0 - (do not remove this comment)
(script# 702)
(include game.sh) (include "700.shm")
(use Main)
(use TellerIcon)
(use PAvoid)
(use PolyPath)
(use StopWalk)
(use System)

(public
	egoTell 0
	startUp 1
)

(local
	local0 = [0 -74 -75 37 -55 -73 32 28 -72 56 999]
	[local11 5]
	local16 = [0 -25 -76 -27 -26 -28 -33 -34 -39 -40 -41 -42 -43 -44 48 -49 52 -53 999]
	local35 = [0 -29 -30 -31 -32 999]
	local41 = [0 -22 -23 -24 999]
	local46 = [0 -50 999]
	local49 = [0 51 999]
	local52 = [0 54 999]
	[local55 16]
	local71 = [0 -25 -28 -49 -53 -50 999]
	toX
	local79
	johariWasHere
	local81
	local82
	egoWasMoving
)
(instance johariExit of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID JOHARI_TALKER 1) setMotion: PolyPath toX 145 self)
				(= johariWasHere TRUE)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goToVillage of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fGoWithJohari)
				(HandsOff)
				((ScriptID JOHARI_TALKER 1) setMotion: PolyPath toX 145 self)
				(ego setAvoider: PAvoider setMotion: PolyPath toX 165)
			)
			(1
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance castLightning of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 10)
			)
			(1
				(messager say: N_JOHARI V_DOIT C_CAST_LIGHTNING)
				(ego learn: LIGHTNING 10 solvePuzzle: fLearnLightning 4 puzzleWIZARD)
				(self dispose:)
			)
		)
	)
)

(instance startUp of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 3)
			)
			(1
				(if (Btst fJohariWatchingEgo)
					(cond 
						((& global431 $4000)
							(messager say: N_CUE V_DOIT C_HEAR_SOMETHING 0 self)
						)
						((Btst fIgnoredJohari)
							(messager say: N_JOHARI V_DOIT C_IGNORED_JOHARI 0 self)
						)
						(else
							(messager say: N_JOHARI V_DOIT C_GREETED_JOHARI 0 self)
						)
					)
				else
					(messager say: N_CUE V_DOIT C_SOMEONE_WATCHING 0 self)
				)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance johariEntrance of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= johariWasHere FALSE)
				(Bset fJohariWatchingEgo)
				(= cycles 2)
			)
			(1
				(if (< (ego x?) 160)
					(ego setMotion: PolyPath 145 145 self)
					(= toX 350)
					((ScriptID JOHARI_TALKER 1)
						view: 975
						x: 350
						y: 145
						init:
						noun: 3
						setScale: -1 ego
						setCycle: StopWalk 974
						setAvoider: PAvoider
						setMotion: PolyPath 175 145 self
					)
				else
					(ego setMotion: PolyPath 175 145 self)
					(= toX -20)
					((ScriptID JOHARI_TALKER 1)
						view: 975
						x: -20
						y: 145
						noun: 3
						init:
						setScale: -1 ego
						setAvoider: PAvoider
						setCycle: StopWalk 974
						setMotion: PolyPath 145 145 self
					)
				)
			)
			(2
				(if (ego mover?)
					(= egoWasMoving TRUE)
				else
					(Face ego (ScriptID JOHARI_TALKER 1))
				)
			)
			(3
				(if egoWasMoving
					(Face ego (ScriptID JOHARI_TALKER 1))
				)
				(= cycles 10)
			)
			(4
				(cond 
					((& global431 $8000)
						(messager say: N_JOHARI V_DOIT C_FIRST_SPEECH 0 self 700)
					)
					((& global431 $4000)
						(messager say: N_JOHARI V_DOIT C_SECOND_SPEECH 0 self 700)
					)
					(else
						(self cue:)
					)
				)
			)
			(5
				(= [local55 0] @local16)
				(= [local55 1] @local35)
				(= [local55 2] @local41)
				(= [local55 3] @local46)
				(= [local55 4] @local49)
				(= [local55 5] @local52)
				(johariTell
					init: (ScriptID JOHARI_TALKER 1) @local16 @local55 @local71
				)
				(= cycles 5)
			)
			(6
				(HandsOn)
				(= cycles 1)
			)
			(7
				(theIconBar disable: ICON_CAST)
				(self dispose:)
			)
		)
	)
)

(instance johariTell of Teller

	(method (showDialog)
		(return
			(if (== local81 local82)
				(if (& global431 $8000)
					(messager say: N_JOHARI V_DOIT C_TALK_TOO_MUCH)
				else
					(messager say: N_JOHARI V_DOIT C_JOHARI_LEAVES)
				)
				(Bset fMetJohariInJungle)
				((ScriptID JOHARI_TALKER 1) setScript: johariExit)
				(return -999)
			else
				(super
					showDialog:
						-25 (& global431 $8000)
						-76 (& global431 $8000)
						-27 (& global431 $8000)
						-26 (& global431 $8000)
						-28 (& global431 $8000)
						-33 (& global431 $8000)
						-34 (if (& global431 $8000) [egoStats MAGIC] else 0)
						-39 (& global431 $4000)
						-40 (& global431 $4000)
						-41 (& global431 $4000)
						-42 (& global431 $4000)
						-43 (& global431 $4000)
						-44 (if (& global431 $4000) [egoStats MAGIC] else 0)
						48 (& global431 $2000)
						-49 (& global431 $2000)
						52 (& global431 $2000)
						-53 (& global431 $2000)
				)
			)
		)
	)
	
	(method (doChild param1 &tmp [temp0 10])
		(return
			(switch param1
				(-25
					(|= local81 $0001)
					(super doChild: param1)
				)
				(-76
					(|= local81 $0002)
					(return 1)
				)
				(-27
					(|= local81 $0004)
					(return 1)
				)
				(-26
					(= local81 $0008)
					(return 1)
				)
				(-28
					(= local81 $0010)
					(super doChild: param1)
				)
				(-33
					(= local81 $0020)
					(return TRUE)
				)
				(-34
					(|= local81 $0040)
					(return TRUE)
				)
				(-39
					(|= local81 $0001)
					(return TRUE)
				)
				(-40
					(|= local81 $0002)
					(return 1)
				)
				(-41
					(|= local81 $0004)
					(return 1)
				)
				(-42
					(|= local81 $0080)
					(return 1)
				)
				(-43
					(|= local81 $0100)
					(return 1)
				)
				(-44
					(|= local81 $0040)
					((ScriptID JOHARI_TALKER 1) setScript: castLightning)
					(return TRUE)
				)
				(-29
					(|= local81 $0200)
					(return TRUE)
				)
				(-30
					(|= local81 $0400)
					(return TRUE)
				)
				(-31
					(|= local81 $0800)
					(return TRUE)
				)
				(-32
					(|= local81 $1000)
					(return TRUE)
				)
				(-22
					(|= local81 $2000)
					(return TRUE)
				)
				(-23
					(|= local81 $4000)
					(return TRUE)
				)
				(-24
					(|= local81 $8000)
					(return TRUE)
				)
				(else
					(super doChild: param1)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_MAGICDRUM)
			(messager say: N_JOHARI V_MAGICDRUM)
			(ego solvePuzzle: fTellAboutDrum 8 setScript: goToVillage)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance egoTell of Teller
	
	(method (init)
		(if (& global431 $2000)
			(= local79 1)
			(ego setScript: johariEntrance)
		)
		(cond 
			((& global431 $8000)
				(if [egoStats MAGIC]
					(= local82 -385)
				else
					(= local82 -449)
				)
			)
			([egoStats MAGIC]
				(= local82 455)
			)
			(else
				(= local82 391)
			)
		)
		(theIconBar disable: ICON_CAST)
		(= [local11 0] @local0)
		(super init: ego @local0 @local11)
	)
	
	(method (showDialog)
		(return
			(if (not johariWasHere)
				(super
					showDialog:
						-74 (if (& global431 $8000) (not local79) else 0)
						-75 (if (& global431 $4000) (not local79) else 0)
						37 (if (& global431 $8000) local79 else 0)
						-55 (if (ego has: iMagicDrum) local79 else 0)
						-73 (if (& global431 $2000) local79 else 0)
						32 (if (& global431 $4000) local79 else 0)
						28 (if (& global431 $4000) local79 else 0)
						-72
						(if (or (& global431 $8000) (& global431 $4000))
							local79
						else
							0
						)
						56 (if (& global431 $2000) local79 else 0)
				)
			else
				(client doVerb: V_TALK)
				(return -999)
			)
		)
	)
	
	(method (doChild param1)
		(cond 
			((== param1 -74)
				(= local79 1)
				(ego
					solvePuzzle: fGreetJohari 3
					setScript: johariEntrance
				)
			)
			((== param1 -75)
				(= local79 1)
				(ego setScript: johariEntrance)
			)
			((== param1 -55)
				(ego setScript: goToVillage)
			)
			((== param1 -72)
				(Bset 105)
				(ego setScript: johariExit)
			)
			((== param1 -73)
				(ego
					solvePuzzle: fTellAboutDrum 8
					setScript: goToVillage
				)
			)
		)
		(return TRUE)
	)
)

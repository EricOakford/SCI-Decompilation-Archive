;;; Sierra Script 1.0 - (do not remove this comment)
(script# 702)
(include sci.sh)
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
	[local0 11] = [0 -74 -75 37 -55 -73 32 28 -72 56 999]
	[local11 5]
	[local16 19] = [0 -25 -76 -27 -26 -28 -33 -34 -39 -40 -41 -42 -43 -44 48 -49 52 -53 999]
	[local35 6] = [0 -29 -30 -31 -32 999]
	[local41 5] = [0 -22 -23 -24 999]
	[local46 3] = [0 -50 999]
	[local49 3] = [0 51 999]
	[local52 3] = [0 54 999]
	[local55 16]
	[local71 7] = [0 -25 -28 -49 -53 -50 999]
	local78
	local79
	local80
	local81
	local82
	local83
)
(instance johariExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 36 1) setMotion: PolyPath local78 145 self)
				(= local80 1)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance goToVillage of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 92)
				(HandsOff)
				((ScriptID 36 1) setMotion: PolyPath local78 145 self)
				(ego setAvoider: PAvoider setMotion: PolyPath local78 165)
			)
			(1
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance castLightning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(messager say: 3 6 88)
				(ego learn: 32 10 solvePuzzle: 318 4 2)
				(self dispose:)
			)
		)
	)
)

(instance startUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(if (Btst 102)
					(cond 
						((& global431 $4000) (messager say: 4 6 60 0 self))
						((Btst 103) (messager say: 3 6 46 0 self))
						(else (messager say: 3 6 47 0 self))
					)
				else
					(messager say: 4 6 63 0 self)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance johariEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local80 0)
				(Bset 102)
				(= cycles 2)
			)
			(1
				(if (< (ego x?) 160)
					(ego setMotion: PolyPath 145 145 self)
					(= local78 350)
					((ScriptID 36 1)
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
					(= local78 -20)
					((ScriptID 36 1)
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
					(= local83 1)
				else
					(Face ego (ScriptID 36 1))
				)
			)
			(3
				(if local83 (Face ego (ScriptID 36 1)))
				(= cycles 10)
			)
			(4
				(cond 
					((& global431 $8000) (messager say: 3 6 21 0 self 700))
					((& global431 $4000) (messager say: 3 6 38 0 self 700))
					(else (self cue:))
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
					init: (ScriptID 36 1) @local16 @local55 @local71
				)
				(= cycles 5)
			)
			(6 (HandsOn) (= cycles 1))
			(7
				(theIconBar disable: 6)
				(self dispose:)
			)
		)
	)
)

(instance johariTell of Teller
	(properties)
	
	(method (showDialog)
		(return
			(if (== local81 local82)
				(if (& global431 $8000)
					(messager say: 3 6 35)
				else
					(messager say: 3 6 45)
				)
				(Bset 105)
				((ScriptID 36 1) setScript: johariExit)
				(return -999)
			else
				(super
					showDialog:
						-25
						(& global431 $8000)
						-76
						(& global431 $8000)
						-27
						(& global431 $8000)
						-26
						(& global431 $8000)
						-28
						(& global431 $8000)
						-33
						(& global431 $8000)
						-34
						(if (& global431 $8000) [egoStats 12] else 0)
						-39
						(& global431 $4000)
						-40
						(& global431 $4000)
						-41
						(& global431 $4000)
						-42
						(& global431 $4000)
						-43
						(& global431 $4000)
						-44
						(if (& global431 $4000) [egoStats 12] else 0)
						48
						(& global431 $2000)
						-49
						(& global431 $2000)
						52
						(& global431 $2000)
						-53
						(& global431 $2000)
				)
			)
		)
	)
	
	(method (doChild param1 &tmp [temp0 10])
		(return
			(switch param1
				(-25
					(= local81 (| local81 $0001))
					(super doChild: param1)
				)
				(-76
					(= local81 (| local81 $0002))
					(return 1)
				)
				(-27
					(= local81 (| local81 $0004))
					(return 1)
				)
				(-26
					(= local81 (| local81 $0008))
					(return 1)
				)
				(-28
					(= local81 (| local81 $0010))
					(super doChild: param1)
				)
				(-33
					(= local81 (| local81 $0020))
					(return 1)
				)
				(-34
					(= local81 (| local81 $0040))
					(return 1)
				)
				(-39
					(= local81 (| local81 $0001))
					(return 1)
				)
				(-40
					(= local81 (| local81 $0002))
					(return 1)
				)
				(-41
					(= local81 (| local81 $0004))
					(return 1)
				)
				(-42
					(= local81 (| local81 $0080))
					(return 1)
				)
				(-43
					(= local81 (| local81 $0100))
					(return 1)
				)
				(-44
					(= local81 (| local81 $0040))
					((ScriptID 36 1) setScript: castLightning)
					(return 1)
				)
				(-29
					(= local81 (| local81 $0200))
					(return 1)
				)
				(-30
					(= local81 (| local81 $0400))
					(return 1)
				)
				(-31
					(= local81 (| local81 $0800))
					(return 1)
				)
				(-32
					(= local81 (| local81 $1000))
					(return 1)
				)
				(-22
					(= local81 (| local81 $2000))
					(return 1)
				)
				(-23
					(= local81 (| local81 $4000))
					(return 1)
				)
				(-24
					(= local81 (| local81 $8000))
					(return 1)
				)
				(else  (super doChild: param1))
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 57)
			(messager say: 3 57)
			(ego solvePuzzle: 320 8 setScript: goToVillage)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (init)
		(if (& global431 $2000)
			(= local79 1)
			(ego setScript: johariEntrance)
		)
		(cond 
			((& global431 $8000)
				(if [egoStats 12]
					(= local82 -385)
				else
					(= local82 -449)
				)
			)
			([egoStats 12] (= local82 455))
			(else (= local82 391))
		)
		(theIconBar disable: 6)
		(= [local11 0] @local0)
		(super init: ego @local0 @local11)
	)
	
	(method (showDialog)
		(return
			(if (not local80)
				(super
					showDialog:
						-74
						(if (& global431 $8000) (not local79) else 0)
						-75
						(if (& global431 $4000) (not local79) else 0)
						37
						(if (& global431 $8000) local79 else 0)
						-55
						(if (ego has: 46) local79 else 0)
						-73
						(if (& global431 $2000) local79 else 0)
						32
						(if (& global431 $4000) local79 else 0)
						28
						(if (& global431 $4000) local79 else 0)
						-72
						(if (or (& global431 $8000) (& global431 $4000))
							local79
						else
							0
						)
						56
						(if (& global431 $2000) local79 else 0)
				)
			else
				(client doVerb: 2)
				(return -999)
			)
		)
	)
	
	(method (doChild param1)
		(cond 
			((== param1 -74)
				(= local79 1)
				(ego solvePuzzle: 319 3 setScript: johariEntrance)
			)
			((== param1 -75) (= local79 1) (ego setScript: johariEntrance))
			((== param1 -55) (ego setScript: goToVillage))
			((== param1 -72) (Bset 105) (ego setScript: johariExit))
			((== param1 -73) (ego solvePuzzle: 320 8 setScript: goToVillage))
		)
		(return 1)
	)
)

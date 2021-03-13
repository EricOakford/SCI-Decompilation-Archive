;;; Sierra Script 1.0 - (do not remove this comment)
(script# 285)
(include game.sh) (include "285.shm")
(use Main)
(use TellerIcon)
(use Talker)
(use Feature)
(use LoadMany)
(use Game)
(use Actor)
(use System)

(public
	rm285 0
	kreeshaBTalker 1
	rakeeshBTalker 2
)

(local
	greeting
	[local1 19] = [0 -1 -2 -3 -4 -7 -54 -55 -56 -59 -9 -48 -61 -78 -67 -79 -69 -70 999]
	[local20 14]
	[local34 14] = [0 -1 -4 -7 -5 -45 -46 -10 -40 -11 -56 -57 -9 999]
	[local48 3] = [0 -57 999]
	[local51 3] = [0 58 999]
	[local54 3] = [0 -5 999]
	[local57 3] = [0 6 999]
	[local60 7] = [0 -45 -40 -10 43 21 999]
	[local67 3] = [0 -46 999]
	[local70 3] = [0 47 999]
	[local73 3] = [0 -11 999]
	[local76 3] = [0 17 999]
	[local79 3] = [0 49 999]
	[local82 4] = [0 44 16 999]
	[local86 3] = [0 18 999]
	[local89 18] = [0 -1 -20 -21 -22 -23 -56 -60 -30 -37 -62 -40 -63 -64 -71 -72 -75 999]
	[local107 9]
	[local116 10] = [0 -1 -20 -24 -50 -48 -52 -62 -72 999]
	[local126 7] = [0 74 73 999 0 65 999]
	[local133 3] = [0 -52 999]
	[local136 4] = [0 -24 -50 999]
	[local140 6] = [0 9 45 7 -48 999]
	[local146 3] = [0 51 999]
	[local149 4] = [0 10 49 999]
	[local153 3] = [0 53 999]
	[local156 14] = [0 -41 -76 1 -30 -77 -33 -34 -35 -36 -37 -42 -80 999]
	[local170 4]
)

(enum ;greetings
	beforeThief
	afterThief
	afterSimbani
	genericGreet
)

(instance rm285 of Room
	(properties
		noun N_ROOM
		picture 285
	)
	
	(method (init)
		(= [local20 0] @local1)
		(= [local20 1] @local54)
		(= [local20 2] @local86)
		(= [local20 3] @local60)
		(= [local20 4] @local57)
		(= [local20 5] @local67)
		(= [local20 6] @local70)
		(= [local20 7] @local82)
		(= [local20 8] @local73)
		(= [local20 9] @local76)
		(= [local20 10] @local48)
		(= [local20 11] @local51)
		(= [local20 12] @local79)
		(= [local107 0] @local89)
		(= [local107 1] @local136)
		(= [local107 2] @local133)
		(= [local107 3] @local140)
		(= [local107 4] @local146)
		(= [local107 5] @local149)
		(= [local107 6] @local153)
		(= [local107 8] @local126)
		(= [local170 0] @local156)
		(walkHandler addToFront: curRoom)
		(HandsOn)
		(theIconBar disable: ICON_INVENTORY ICON_USEIT ICON_ACTIONS ICON_CAST)
		(super init:)
		(cSound number: 285 setLoop: -1 play: 127)
		(pillar init:)
		(kreesha init:)
		(rakeeshFeat init:)
		(ego noun: 3)
		(egoDude actions: egoTell init:)
		(egoTell init: ego @local156 @local170)
		(kreeshaTell init: kreesha @local89 @local107 @local116)
		(rakeeshTell init: rakeeshFeat @local1 @local20 @local34)
		(cond 
			((not (Btst fMetMoneyChanger))
				(= greeting beforeThief)
			)
			((not (Btst fRakeeshSworeOath))
				(= greeting afterThief)
			)
			((not (Btst fReturnedToRakeesh))
				(= greeting afterSimbani)
				(Bset fReturnedToRakeesh)
			)
			(else
				(= greeting genericGreet)
			)
		)
		(self setScript: theyConverse)
	)
	
	(method (dispose)
		((ego actions?) dispose:)
		(ego actions: 0)
		(LoadMany FALSE RAKEESH_TALKER KREESHA_TALKER)
		(walkHandler delete: curRoom)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(curRoom newRoom: 280)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance egoExits of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 2)
			)
			(1
				(curRoom newRoom: 280)
			)
		)
	)
)

(instance theyConverse of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(switch greeting
					(beforeThief
						(messager say: N_RAKEESH V_DOIT C_BEFORE_THIEF 0 self)
					)
					(afterThief
						(messager say: N_RAKEESH V_DOIT C_AFTER_THIEF 0 self)
					)
					(afterSimbani
						(messager say: N_RAKEESH V_DOIT C_AFTER_SIMBANI 0 self)
					)
					(genericGreet
						(messager say: N_RAKEESH V_DOIT C_GENERIC 0 self)
					)
					(else
						(self cue:)
					)
				)
			)
			(2 0)
			(3
				(curRoom newRoom: 280)
			)
		)
	)
)

(instance kreesha of Feature
	(properties
		x 100
		y 100
		noun N_KREESHA
		onMeCheck cBLUE
	)
)

(instance rakeeshFeat of Feature
	(properties
		x 100
		y 100
		noun N_RAKEESH
		onMeCheck cGREEN
	)
)

(instance rakeeshArm of Feature
	(properties
		x 168
		y 83
		noun N_RAKEESH
		nsTop 61
		nsLeft 158
		nsBottom 105
		nsRight 179
		sightAngle 180
	)
)

(instance pillar of Feature
	(properties
		x 44
		y 73
		noun N_PILLAR
		nsTop 23
		nsLeft 30
		nsBottom 123
		nsRight 59
		sightAngle 180
	)
)

(instance egoDude of Feature
	(properties
		x 100
		y 100
		noun N_EGO
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(ego doVerb: theVerb)
	)
)

(instance egoTell of Teller
	
	(method (showDialog)
		(super
			showDialog:
				-30 (Btst fVisitedSimbani)	;NOTE: This used flag 42, which is never set, making it impossible to get this.
				-31
				(Btst fAfterRace) 77
				(if (Btst fLeopardmanCaptured) (not (Btst fDispelledLeopardman)) else 0) -33
				(if (Btst fDispelledLeopardman) (not (Btst fJohariReleased)) else 0) -34
				(if (== brideState 4) (not (Btst fJohariReleased)) else 0) -35
				(if (not (Btst fEnteredLeopardmanVillage)) (Btst fJohariReleased) else 0) -36
				(if (and (Btst fEnteredLeopardmanVillage) (Btst fJohariReleased))
					(not (Btst fSawLeopardmanRitual))
				else
					0
				)
				-37
				(Btst fSawLeopardmanRitual)
				-41
				(< greeting 3)
				-76
				(< greeting 3)
				-42
				(== greeting 3)
				-80
				(== greeting 3)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-76
					(curRoom setScript: egoExits)
					(return query)
				)
				(-80
					(curRoom setScript: egoExits)
					(return query)
				)
				(-31
					(ego solvePuzzle: fTellAboutInitiation 3 (| puzzleFIGHTER puzzlePALADIN))
					(return query)
				)
				(-33
					(ego solvePuzzle: fTellAboutDispelledLeopardLady 2)
					(return query)
				)
				(else  (return query))
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_WALK)
			(curRoom doVerb: V_WALK)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance kreeshaTell of Teller

	(method (showDialog)
		(super
			showDialog:
				-1
				(== greeting 0)
				-20
				(== greeting 0)
				-21
				(== greeting 0)
				-22
				(== greeting 0)
				-23
				(== greeting 0)
				-56
				(== greeting 1)
				-60
				(== greeting 1)
				-30
				(== greeting 1)
				-37
				(== greeting 1)
				-62
				(== greeting 2)
				-40
				(== greeting 2)
				-63
				(== greeting 2)
				-64
				(== greeting 2)
				-71
				(== greeting 3)
				-72
				(== greeting 3)
				-75
				(== greeting 3)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-1 (super doChild: query))
				(-24 (super doChild: query))
				(-45 (super doChild: query))
				(-48 (super doChild: query))
				(-50 (super doChild: query))
				(-20 (super doChild: query))
				(-52 (super doChild: query))
				(-72 (super doChild: query))
				(else  (return query))
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_WALK)
			(curRoom doVerb: V_WALK)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance rakeeshTell of Teller

	(method (showDialog)
		(super
			showDialog:
				-1
				(== greeting 0)
				-2
				(== greeting 0)
				-3
				(== greeting 0)
				-4
				(== greeting 0)
				-7
				(== greeting 0)
				-56
				(== greeting 1)
				-54
				(== greeting 1)
				-55
				(== greeting 1)
				-59
				(== greeting 1)
				-9
				(== greeting 2)
				-48
				(== greeting 2)
				-61
				(== greeting 2)
				-78
				(== greeting 2)
				-67
				(== greeting 3)
				-79
				(== greeting 3)
				-69
				(== greeting 3)
				-70
				(== greeting 3)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-1 (super doChild: query))
				(-4 (super doChild: query))
				(-5 (super doChild: query))
				(-7 (super doChild: query))
				(-40 (super doChild: query))
				(-9 (super doChild: query))
				(-10 (super doChild: query))
				;NOTE: This case already exists, and will probably never be executed
				;(-7 (super doChild: query))
				(-45 (super doChild: query))
				(-46 (super doChild: query))
				(-56 (super doChild: query))
				(-57 (super doChild: query))
				(else  (return query))
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_WALK)
			(curRoom doVerb: V_WALK)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance kreeshaBTalker of Talker
	(properties
		x 127
		y 59
		view 285
		talkWidth 260
		color 37
		back 57
		textX -105
		textY 150
	)
	
	(method (init)
		(super init: 0 0 kreeshaBMouth &rest)
	)
)

(instance kreeshaBMouth of Prop
	(properties
		x 127
		y 59
		view 285
	)
)

(instance rakeeshBTalker of Talker
	(properties
		x 149
		y 50
		view 285
		loop 1
		talkWidth 260
		back 57
		textX -120
		textY 150
	)
	
	(method (init)
		(super init: 0 0 rakeeshBMouth &rest)
	)
)

(instance rakeeshBMouth of Prop
	(properties
		x 149
		y 50
		view 285
		loop 1
	)
)

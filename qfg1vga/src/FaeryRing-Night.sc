;;; Sierra Script 1.0 - (do not remove this comment)
(script# 170)
(include game.sh) (include "170.shm")
(use Main)
(use Meeps)
(use CastFlame)
(use CastDagger)
(use CastCalm)
(use CastDazzle)
(use Teller)
(use Procs)
(use Print)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm170 0
	f1 1
	f2 2
	f3 3
	f4 4
	f5 5
)

(local
	local0
	local1
	local2
	local3
	oldEgoX
	oldEgoY
	local6 =  -1
	oldCycleSpeed
	oldMoveSpeed
	talkCount
	[local10 7]
	askedForDust
	saidNotSure
	local19 = [0 0 0 0 0 0 0 0 62 119 91 145 134 104 75 40 109 108 108 107 101 98 97 101]
	[faery 5]
	faeryStartX = [74 143 52 90 110]
	faeryStartY = [109 120 122 99 108 1 11 6 5 8 14 2 10 15 12]
	[faeryScript 5]
	[chaseScript 5]
	[faeryTalker 5]
	beforeMeetTime
	faeryX = [35 250 265 140 225]
	faeryY = [30 35 125 50 45]
	local94
	local95
	egoDanceLoop
	local97
	enteredCircle
	couldntGetDust
	local100 = [-1 0 13 22 32 38 47 59 69 78 104 113 121 128 135 141 151]
	local117
	local118
	[local119 2]
	local121 = [0 -18 15 17 19 3 999]
	local128 = [0 16 999]
	[local131 7]
	local138 = [0 -18 999]
)

;EO: These procedures were really messy when decompiled!
(procedure (FaeryAttention &tmp i)
	(if (not local1)
		(= local1 1)
		(fairyFeat init:)
		(= local6 100)
	)
	(Bset fFaeryAttention)
	(ego signal: (| (ego signal?) skipCheck)
	(for ((= i 0)) (< i 4) ((++ i))
		(= [chaseScript i] (Clone aChaseScript))
		([faery i]
			setStep: 6 4
			setPri: 14
			setScript: [chaseScript i] 0)
		)
	)
)

(procedure (FaerysDance &tmp i)
	(Bclr fFaeryAttention)
	(for ((= i 0)) (< i 4) ((++ i))
		(= [faeryScript i] (aFaeryScript new:))
		([faery i]
			setStep: 3 2
			setScript: [faeryScript i] 0
		)
	)
)

(procedure (FaerysAlarmed &tmp i temp1)
	(Bclr fFaeryAttention)
	(= beforeMeetTime 80)
	(for ((= i 0)) (< i 4) ((++ i))
		(= [faeryScript i] (aFaeryScript new:))
		(= temp1 (Random 0 4))
		([faery temp1]
			posn: [faeryX temp1] [faeryY temp1]
			setScript: [faeryScript i] 0 temp1
		)
	)
)

(procedure (AddFaerys &tmp i)
	(for ((= i 0)) (< i 4) ((++ i))
		(= [faeryScript i] (aFaeryScript new:))
		(= [faeryTalker i] (fairyTeller new:))
		(= [faery i] (Actor new:))
		([faery i]
			view: 70
			setLoop: i
			cel: 0
			posn: [faeryStartX i] [faeryStartY i]
			ignoreActors: TRUE
			illegalBits: 0
			ignoreHorizon: TRUE
			init:
			actions: [faeryTalker i]
			setCycle: Forward
			setScript: [faeryScript i] 0 i
			setPri: 14
		)
	)
)
(class FaeryScript of Script
	(properties
		name "Script#"
		register2 -1
	)
)

(instance roomTimer of Timer)

(instance rm170 of Room
	(properties
		picture 70
		style HSHUTTER
		horizon 37
		north 62
	)
	
	(method (init &tmp temp0)
		(= [local131 0] @local121)
		(= [local131 1] @local128)
		(= [local131 2] 999)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						63 71
						230 71
						230 88
						254 88
						254 144
						241 144
						241 156
						139 156
						139 123
						160 114
						132 102
						88 102
						81 111
						59 111
						57 103
						39 92
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						59 140
						125 140
						125 161
						59 161
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						66 115
						102 115
						132 121
						132 138
						97 124
						66 124
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						94 0
						94 54
						0 54
						0 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 0
						319 54
						149 54
						150 -3
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 137
						33 157
						39 184
						110 184
						110 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						163 189
						163 187
						319 187
						319 189
					yourself:
				)
		)
		(= egoDanceLoop 0)
		(LoadMany VIEW 70 71)
		(Load SCRIPT DPATH)
		(LoadMany SOUND 40 64)
		(LoadMany VIEW 510 72)
		(= local2 1)
		(fairyTeller init: aFaery @local121 @local131 @local138)
		(aFaery init:)
		(AddFaerys)
		(cSound stop:)
		(faeryMusic init: play:)
		(egoBoogie init:)
		(bopTilYouDrop init:)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
		(switch prevRoomNum
			(62
				(curRoom setScript: sEnterFromNorth)
			)
			(71
				(curRoom setScript: sEnterFromEast)
			)
			(69
				(curRoom setScript: sEnterFromWest)
			)
			(999
				(ego posn: (ego x?) (ego y?) init:)
			)
			(else 
				(curRoom setScript: sEnterFromSouth)
			)
		)
		(mush1 init:)
		(mush2 init:)
		(mush3 init:)
		(mush4 init:)
		(mush5 init:)
		(mush6 init:)
		(mush7 init:)
		(ring init:)
		(rocks init:)
		(treesRight init:)
		(treesLeft init:)
		(theRoom init:)
		(firstTime init:)
	)
	
	(method (doit)
		(cond 
			(script)
			((> (ego x?) 310)
				(HandsOff)
				(curRoom setScript: sExitEast)
			)
			((< (ego x?) 10)
				(HandsOff)
				(curRoom setScript: sExitWest)
			)
			((> (ego y?) 185)
				(HandsOff)
				(curRoom setScript: sExitSouth)
			)
			((== local2 0)
				(= local2 1)
				(aFaery init:)
				(fairyFeat init:)
				(AddFaerys)
				(cSound stop:)
				(faeryMusic init: play:)
				(egoBoogie init:)
				(bopTilYouDrop init:)
			)
		)
		(cond 
			((== beforeMeetTime 70)
				(-- beforeMeetTime)
				(= local117 1)
				(= local94 8)
				(curRoom setScript: meetFaery)
			)
			((> beforeMeetTime 1)
				(-- beforeMeetTime)
			)
			((== beforeMeetTime 1)
				(= beforeMeetTime 0)
			)
		)
		(if
			(and
				(== (ego onControl: origin) cYELLOW)
				(== (User canControl:) TRUE)
				(not enteredCircle)
			)
			(= enteredCircle TRUE)
			(FaeryAttention)
			(= saidNotSure 0)
			(= local117 1)
			(= local94 7)
			(curRoom setScript: meetFaery)
		)
		(if (and (== local94 8) (== (curRoom script?) 0))
			(= local117 1)
			(= local94 3)
			(curRoom setScript: meetFaery)
		)
		(super doit:)
		(if (not (-- local6))
			(= local6 100)
			(if (or (!= oldEgoX (ego x?)) (!= oldEgoY (ego y?)))
				(= oldEgoX (ego x?))
				(= oldEgoY (ego y?))
				(fairyFeat
					x: (ego x?)
					y: (- (ego y?) 20)
					nsLeft: (- (ego x?) 30)
					nsTop: (- (ego y?) 50)
					nsBottom: (+ (ego y?) 10)
					nsRight: (+ (ego x?) 20)
				)
			)
		)
	)
	
	(method (dispose &tmp temp0)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(DisposeScript DPATH)
		(super dispose:)
	)
	
	(method (cue)
		(if saidNotSure
			(= saidNotSure 0)
			(= local117 1)
			(= local94 2)
			(curRoom setScript: meetFaery)
		)
	)
	
	(method (newRoom n)
		(roomTimer dispose: delete:)
		(if (not local0)
			(Bclr fFaeryAttention)
		)
		(super newRoom: n)
	)
)

(instance faeryMusic of Sound
	(properties
		flags $ffff
		number 40
		priority 1
		loop -1
	)
)

(instance egoBoogie of Sound
	(properties
		flags $ffff
		number 49
		priority 2
		loop -1
	)
)

(instance bopTilYouDrop of Sound
	(properties
		flags $ffff
		number 64
		priority 2
		loop -1
	)
)

(instance fairyFeat of Feature
	(properties
		x 98
		y 109
		z -100
		noun 1
		nsTop 68
		nsLeft 38
		nsBottom 151
		nsRight 158
		sightAngle 40
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(return
			(cond 
				((Btst fFaeryAttention)
					(fairyTeller doVerb: theVerb &rest)
				)
				(local3
					(= local3 -1)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
)

(instance treesLeft of Feature
	(properties
		x 58
		y 94
		noun 6
		nsTop -1
		nsBottom 189
		nsRight 116
		sightAngle 40
		onMeCheck cBLUE
	)
)

(instance treesRight of Feature
	(properties
		x 224
		y 94
		noun 5
		nsTop -1
		nsLeft 130
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck cGREEN
	)
)

(instance rocks of Feature
	(properties
		x 145
		y 101
		noun 4
		nsTop 49
		nsLeft 43
		nsBottom 153
		nsRight 247
		sightAngle 40
		onMeCheck cCYAN
	)
)

(instance mush1 of Feature
	(properties
		x 115
		y 102
		noun 2
		nsTop 84
		nsLeft 79
		nsBottom 102
		nsRight 152
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_MUSHROOMS V_LOOK C_NIGHT)
			)
			(V_DO
				(if (and (not saidNotSure) (not (ego script?)))
					(ego setMotion: PolyPath 123 110)
				else
					(messager say: N_MUSHROOMS V_DO)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mush2 of Feature
	(properties
		x 71
		y 102
		noun 2
		nsTop 95
		nsLeft 64
		nsBottom 109
		nsRight 79
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(ego setMotion: PolyPath 123 110)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance mush3 of Feature
	(properties
		x 80
		y 116
		noun 2
		nsTop 111
		nsLeft 72
		nsBottom 121
		nsRight 89
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(ego setMotion: PolyPath 123 110)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance mush4 of Feature
	(properties
		x 113
		y 123
		noun 2
		nsTop 115
		nsLeft 97
		nsBottom 131
		nsRight 129
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(ego setMotion: PolyPath 123 110)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance mush5 of Feature
	(properties
		x 156
		y 125
		noun 2
		nsTop 119
		nsLeft 144
		nsBottom 131
		nsRight 168
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(ego setMotion: PolyPath 123 110)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance mush6 of Feature
	(properties
		x 163
		y 102
		noun 2
		nsTop 96
		nsLeft 153
		nsBottom 108
		nsRight 174
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(ego setMotion: PolyPath 123 110)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance mush7 of Feature
	(properties
		x 177
		y 116
		noun 2
		nsTop 109
		nsLeft 170
		nsBottom 121
		nsRight 185
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(ego setMotion: PolyPath 123 110)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance ring of Feature
	(properties
		x 137
		y 100
		z 100
		noun N_MUSHROOMS
		sightAngle 40
		onMeCheck cYELLOW
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_MUSHROOMS V_LOOK C_NIGHT)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance aFaery of Actor
	(properties
		noun N_FAIRIES
		view 70
		loop 4
		priority 14
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		illegalBits $0000
	)
)

(instance fairyTeller of Teller
	
	(method (showDialog &tmp ret [temp1 20])
		(if (== (= ret (super showDialog: 3 local0)) 3)
			(curRoom setScript: getDust)
		)
		(return ret)
	)
	
	(method (doVerb theVerb &tmp [temp0 20])
		(return
			(switch theVerb
				(V_LOOK
					(messager say: N_FAIRIES V_LOOK)
					(return TRUE)
				)
				(V_DO
					(messager say: N_FAIRIES V_DO)
					(return TRUE)
				)
				(V_TALK
					(if saidNotSure
						(messager say: N_FAIRIES V_FLASK)
					else
						(++ talkCount)
						(cond 
							((and local3 (< (setUpFaerys state?) 2))
								(= local3 -1)
							)
							((> talkCount 5)
								(if (Btst fFaeryAttention)
									(= local117 1)
									(= local94 14)
									(curRoom setScript: meetFaery)
								else
									(messager say: N_ROOM V_TALK C_FAIRIESIGNORE)
								)
							)
							(else
								(SolvePuzzle POINTS_TALKTOFAIRIES 1)
								(super doVerb: theVerb &rest)
							)
						)
					)
				)
				(V_FLASK
					(cond 
						(saidNotSure
							(messager say: N_FAIRIES V_FLASK)
						)
						((Btst POINTS_GETFAIRYDUST)
							(messager say: N_FAIRIES V_FLASK C_GETDUST)
						)
						(couldntGetDust
							(messager say: N_FAIRIES V_FLASK C_COULDNTGETDUST)
						)
						((not askedForDust)
							(messager say: N_FAIRIES V_FLASK C_DIDNTASKFORDUST)
						)
						(else
							(= local117 1)
							(= local94 9)
							(curRoom setScript: meetFaery)
						)
					)
					(return 1)
				)
				(V_SWORD
					(FaeryAttention)
					(= local117 1)
					(= local94 15)
					(curRoom setScript: meetFaery)
					(return TRUE)
				)
				(V_DAGGER
					(if (ego has: iDagger)
						(CastDagger 0)
						(FaerysAlarmed)
					else
						(messager say: N_FAIRIES V_DAGGER)
					)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance aFaeryScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(== local94 14)
					(HandsOn)
				)
				(if (> beforeMeetTime 0)
					(client
						setMotion: MoveTo
							(Random
								(- [faeryX register] 25)
								(+ [faeryX register] 25)
							)
							(Random
								(- [faeryY register] 25)
								(+ [faeryY register] 25)
							)
							self
					)
				else
					(client
						setMotion: MoveTo
							(Random
								(- [faeryStartX register] 15)
								(+ [faeryStartX register] 15)
							)
							(Random
								(- [faeryStartY register] 15)
								(+ [faeryStartY register] 15)
							)
							self
					)
				)
			)
			(1 (self changeState: 0))
		)
	)
)

(instance aChaseScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					(local118
						(client
							setMotion: MoveTo
								(- (Random (ego x?) (+ (ego x?) 10)) 18)
								(- (Random (ego y?) (- (ego y?) 8)) 50)
								self
						)
					)
					((Btst DANCING_FOR_FAIRIES)
						(client
							setMotion: MoveTo
								(- (Random (ego x?) (+ (ego x?) 20)) 10)
								(- (Random (ego y?) (+ (ego y?) 40)) 50)
								self
						)
					)
					(else
						(client
							setMotion: MoveTo
								(- (Random (ego x?) (+ (ego x?) 70)) 40)
								(- (Random (ego y?) (+ (ego y?) 40)) 50)
								self
						)
					)
				)
			)
			(1
				(if (or (== local94 14) (== local94 8))
					(HandsOn)
				)
				(self init:)
			)
		)
	)
)

(instance deathDance of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local95 0)
				(theGame setCursor: waitCursor TRUE)
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: PolyPath 123 113 self
				)
			)
			(1
				(theGame setCursor: waitCursor TRUE)
				(faeryMusic stop:)
				(bopTilYouDrop play:)
				(ego view: 71)
				(self cue:)
			)
			(2
				(ego setLoop: (Random 0 4) cel: 0 setCycle: EndLoop self)
				(++ local95)
			)
			(3
				(cond 
					((== (mod local95 3) 0)
						(messager say: N_FAIRIES NULL C_DANCEAGAIN)
						(self changeState: 2)
					)
					((>= local95 6)
						(= cycles 2)
					)
					(else
						(self changeState: 2)
					)
				)
			)
			(4
				(faeryMusic play:)
				(bopTilYouDrop stop:)
				(ego setLoop: 7 setCel: 1)
				(= cycles 10)
			)
			(5
				(ego cycleSpeed: 6 setCycle: EndLoop)
				(= cycles 35)
			)
			(6
				(messager say: N_FAIRIES NULL C_DEATHDANCE)
				(= seconds 4)
			)
			(7
				(EgoDead C_DIE_FAIRY_DANCE C_DIE_FAIRY_DANCE_TITLE 5 2 71)
			)
		)
	)
)

(instance sExitEast of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 350 (ego y?) self)
			)
			(1
				(curRoom newRoom: 71)
			)
		)
	)
)

(instance sExitWest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo -25 (ego y?) self)
			)
			(1
				(curRoom newRoom: 69)
			)
		)
	)
)

(instance sEnterFromEast of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not Night)
					(FixTime 20)
					(-- state)
					(= seconds 3)
				else
					(ego init: posn: 350 111 setMotion: MoveTo 285 111 self)
				)
			)
			(1
				(curRoom setScript: setUpFaerys)
			)
		)
	)
)

(instance sEnterFromWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not Night)
					(FixTime 20)
					(-- state)
					(= seconds 3)
				else
					(ego init: posn: -25 126 setMotion: MoveTo 57 126 self)
				)
			)
			(1
				(curRoom setScript: setUpFaerys)
			)
		)
	)
)

(instance sEnterFromNorth of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not Night)
					(FixTime 20)
					(-- state)
					(= seconds 3)
				else
					(ego init: posn: 122 39 setMotion: MoveTo 122 51 self)
				)
			)
			(1
				(curRoom setScript: setUpFaerys)
			)
		)
	)
)

(instance theRoom of Feature
	(properties
		x 159
		y 1
		z 94
		nsTop 1
		nsBottom 189
		nsRight 319
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(57
					(messager say: N_ROOM V_TALK)
					(return 0)
				)
				(50
					(messager say: N_ROOM 50 23)
				)
				(V_TALK
					(if (not askedForDust)
						(= askedForDust TRUE)
						(if (Btst DANCED_FOR_FAIRIES)
							(= local94 16)
						else
							(= local94 1)
						)
						(FaeryAttention)
						(curRoom setScript: meetFaery)
					)
				)
				(V_DAZZLE
					(CastDazzle)
					(FaerysAlarmed)
				)
				(V_CALM
					(CastCalm)
				)
				(V_FLAME
					(CastFlame 0)
					(FaerysAlarmed)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance meetFaery of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (or enteredCircle (== local94 3) (== local94 15))
					(if enteredCircle
						(messager say: N_FAIRIES NULL C_ENTEREDCIRCLE 0)
					)
					(curRoom setScript: deathDance)
				else
					(switch local94
						(1
							(Bset DANCED_FOR_FAIRIES)
							(switch
								(Print
									addText: N_ROOM NULL C_ASKDANCE 1 0 0
									addButton: 0 N_ROOM NULL C_SAYNOTSURE 1 0 20
									addButton: 1 N_ROOM NULL C_SAYYES 1 0 40
									addButton: 2 N_ROOM NULL C_SAYNO 1 0 60
									init:
								)
								(0
									(= saidNotSure TRUE)
									(roomTimer setReal: curRoom 6)
									(HandsOn)
									(NormalEgo)
								)
								(1
									(= saidNotSure FALSE)
									(= local117 1)
									(= local94 2)
									(self changeState: 0)
								)
								(2
									(= saidNotSure FALSE)
									(= local117 1)
									(= local94 3)
									(self changeState: 0)
								)
							)
						)
						(16
							(switch
								(Print
									addText: N_ROOM NULL C_ASKDANCE 1 0 0
									addButton: 0 N_FAIRIES NULL C_SAYNOTSURE 1 0 20
									addButton: 1 N_FAIRIES NULL C_SAYYES 1 0 40
									addButton: 2 N_FAIRIES NULL C_SAYNO 1 0 60
									init:
								)
								(0
									(= saidNotSure TRUE)
									(roomTimer setReal: curRoom 6)
									(HandsOn)
									(NormalEgo)
								)
								(1
									(= saidNotSure FALSE)
									(= local117 1)
									(= local94 2)
									(self changeState: 0)
								)
								(2
									(= saidNotSure FALSE)
									(= local117 1)
									(= local94 3)
									(self changeState: 0)
								)
							)
						)
						(2
							(ego setScript: cuteDance)
						)
						(5
							(= local97 1)
						)
						(4
							(= local97 1)
						)
						(8
							(= beforeMeetTime 0)
							(FaeryAttention)
						)
						(9
							(curRoom setScript: getDust)
						)
						(14
							(FaerysDance)
						)
						(else 
							(if (not (ego script?))
								(HandsOn)
								(NormalEgo)
							)
						)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not Night)
					(FixTime 20)
					(-- state)
					(= seconds 3)
				else
					(ego init: posn: 139 245 setMotion: MoveTo 139 180 self)
				)
			)
			(1
				(curRoom setScript: setUpFaerys)
			)
		)
	)
)

(instance f1 of Narrator
	(properties
		color 49
		special TRUE
	)
	
	(method (init)
		(self x: (Random 20 80) y: (Random 20 80))
		(super init: &rest)
	)
)

(instance f2 of Narrator
	(properties
		color 23
		special TRUE
	)
	
	(method (init)
		(self x: (Random 20 80) y: (Random 20 80))
		(super init: &rest)
	)
)

(instance f3 of Narrator
	(properties
		color 29
		special TRUE
	)
	
	(method (init)
		(self x: (Random 20 80) y: (Random 20 80))
		(super init: &rest)
	)
)

(instance f4 of Narrator
	(properties
		color 9
		special TRUE
	)
	
	(method (init)
		(self x: (Random 20 80) y: (Random 20 80))
		(super init: &rest)
	)
)

(instance f5 of Narrator
	(properties
		color 7
		special TRUE
	)
	
	(method (init)
		(self x: (Random 20 80) y: (Random 20 80))
		(super init: &rest)
	)
)

(instance gotoRing of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego y?) 100)
					(ego setMotion: PolyPath 38 94 self)
				else
					(= state (+ state 2))
					(ego setMotion: PolyPath 145 179 self)
				)
			)
			(1
				(ego setMotion: MoveTo 54 146 self)
			)
			(2
				(ego setMotion: PolyPath 145 179 self)
			)
			(3
				(self dispose:)
			)
		)
	)
)

(instance firstTime of Feature
	(properties
		x 93
		y 106
		nsTop 70
		nsLeft 43
		nsBottom 143
		nsRight 144
	)
	
	(method (doVerb theVerb)
		(fairyTeller doVerb: theVerb &rest)
	)
)

(instance sExitSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) 245 self)
			)
			(1
				(curRoom newRoom: 77)
			)
		)
	)
)

(instance cuteDance of Script
	
	(method (changeState newState &tmp [temp0 20])
		(switch (= state newState)
			(0
				(HandsOff)
				(theGame setCursor: waitCursor TRUE)
				(Bset DANCING_FOR_FAIRIES)
				(= oldCycleSpeed (ego cycleSpeed?))
				(= oldMoveSpeed (ego moveSpeed?))
				(= local0 1)
				(SolvePuzzle POINTS_DANCEWITHFAIRIES 3)
				(FaerysDance)
				(ego ignoreActors: TRUE illegalBits: 0)
				(self setScript: gotoRing self)
			)
			(1
				(theGame setCursor: waitCursor TRUE)
				(if (TrySkill AGIL 25)
					(messager say: N_FAIRIES NULL C_AGREETODANCE 0 self)
				else
					(messager say: N_FAIRIES NULL C_LOWAGILITY)
					(faeryMusic stop:)
					(egoBoogie play:)
					(ego setScript: klutzDance)
				)
			)
			(2
				(ego
					view: 71
					setLoop: 6
					setCel: 0
					xStep: 1
					yStep: 1
					cycleSpeed: 10
					moveSpeed: 10
					setCycle: Forward
				)
				(self cue:)
			)
			(3
				(++ local95)
				(ego
					setMotion: DPath
						129 161
						144 166
						174 179
						138 185
						107 178
						117 164
						self
				)
			)
			(4
				(cond 
					((== local95 1)
						(FaeryAttention)
						(self changeState: 3)
					)
					((== local95 2)
						(= local117 1)
						(= local94 5)
						(messager say: N_FAIRIES NULL C_CUTEDANCE 0 self)
					)
					(local97
						(= local97 0)
						(= ticks 30)
					)
					((>= local95 3)
						(self changeState: 14)
					)
					(else
						(self changeState: 3)
					)
				)
			)
			(5
				(FaerysDance)
				(ego setLoop: 5 setCel: 0 setCycle: CycleTo 3 1 self)
			)
			(6
				(= seconds 2)
			)
			(7
				(ego setCycle: EndLoop self)
			)
			(8
				(NormalEgo)
				(ego setHeading: 180)
				(= ticks 60)
			)
			(9
				(ego view: 71 setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(10
				(messager say: N_FAIRIES NULL C_FAIRIESIMPRESSED)
				(= seconds 3)
			)
			(11
				(ego setCycle: BegLoop self)
			)
			(12
				(NormalEgo)
				(ego loop: 2)
				(= cycles 5)
			)
			(13
				(FaeryAttention)
				(= local117 1)
				(= local94 6)
				(curRoom setScript: meetFaery)
				(HandsOn)
				(ego
					cycleSpeed: oldCycleSpeed
					moveSpeed: oldMoveSpeed
					setHeading: 180
				)
				(messager say: N_FAIRIES NULL C_WHATDOYOUWANT)	;restored behavior from EGA original
				(self dispose:)
			)
			(14
				(messager say: N_ROOM NULL C_DANCEAGAIN)
				(= local95 0)
				(HandsOn)
				(NormalEgo)
				(ego
					loop: 2
					cycleSpeed: oldCycleSpeed
					moveSpeed: oldMoveSpeed
					setHeading: 180
				)
				(self dispose:)
			)
		)
	)
)

(instance klutzDance of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset DANCING_FOR_FAIRIES)
				(= local0 1)
				(if (== egoDanceLoop 5)
					(= egoDanceLoop 0)
				)
				(ego
					view: 71
					setLoop: egoDanceLoop
					cel: 0
					cycleSpeed: (if enteredCircle 0 else 6)
					setCycle: EndLoop self
				)
				(++ local95)
				(++ egoDanceLoop)
			)
			(1
				(cond 
					((== local95 6)
						(= local117 1)
						(= local94 4)
						(messager say: N_FAIRIES NULL C_KLUTZDANCE 0 self)
					)
					(local97
						(= local97 0)
						(faeryMusic play:)
						(egoBoogie stop:)
						(= ticks 60)
					)
					((>= local95 10)
						(self changeState: 4)
					)
					(else
						(self init:)
					)
				)
			)
			(2
				(HandsOn)
				(NormalEgo)
				(ego
					loop: 2
					cycleSpeed: oldCycleSpeed
					moveSpeed: oldMoveSpeed
					setHeading: 180
				)
				(= cycles 10)
			)
			(3
				(faeryMusic play:)
				(egoBoogie stop:)
				(FaeryAttention)
				(= local117 1)
				(= local94 6)
				(messager say: N_FAIRIES NULL C_WHATDOYOUWANT)
				(self dispose:)
			)
			(4
				(faeryMusic play:)
				(egoBoogie stop:)
				(messager say: N_ROOM NULL C_DANCEAGAIN)
				(= local95 0)
				(HandsOn)
				(NormalEgo)
				(ego
					loop: 2
					cycleSpeed: oldCycleSpeed
					moveSpeed: oldMoveSpeed
					setHeading: 180
				)
				(self dispose:)
			)
		)
	)
)

(instance setUpFaerys of FaeryScript
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== state 1)
				(or (!= (ego x?) register) (!= (ego y?) register2))
			)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(NormalEgo)
				(= local3 1)
				(ChangeGait MOVE_WALK)
				(= register (ego x?))
				(= register2 (ego y?))
				(= cycles 2)
			)
			(1)
			(2
				(HandsOff)
				(ego setMotion: 0 setCycle: 0)
				(NormalEgo)
				(= local3 0)
				(= ticks 30)
			)
			(3
				(switch prevRoomNum
					(62
						(ego setHeading: 180 self)
					)
					(69
						(ego setHeading: 90 self)
					)
					(71
						(ego setHeading: 270 self)
					)
					(else 
						(ego setHeading: 360 self)
					)
				)
				(= local6 100)
			)
			(4
				(firstTime dispose:)
				(= cycles 2)
			)
			(5
				(if (not askedForDust)
					(= askedForDust TRUE)
					(if (Btst DANCED_FOR_FAIRIES)
						(= local94 16)
					else
						(= local94 1)
					)
					(FaeryAttention)
					(= ticks 30)
				else
					(self dispose:)
				)
			)
			(6
				(if (Btst DANCED_FOR_FAIRIES)
					(messager say: N_FAIRIES NULL C_HELLOAGAIN 0 self)
				else
					(Bset DANCED_FOR_FAIRIES)
					(messager say: N_FAIRIES NULL C_FIRSTMEET 0 self)
				)
			)
			(7
				(curRoom setScript: meetFaery)
			)
		)
	)
)

(instance getDust of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= couldntGetDust TRUE)
				(ego view: 72 loop: 0 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(1
				(messager say: N_ROOM NULL C_HOLDOUTHAND)
				(= local118 1)
				(= seconds 6)
			)
			(2
				(= local117 1)
				(messager say: N_FAIRIES NULL C_GETDUST 0 self)
			)
			(3
				(= cycles 10)
				(= local118 0)
			)
			(4
				(cond 
					((ego has: iFlask)
						(SolvePuzzle POINTS_GETFAIRYDUST 8)
						(messager say: N_ROOM NULL C_PUTAWAYDUST 0 self)
					)
					(
						(or
							(ego has: iHealingPotion)
							(ego has: iManaPotion)
							(ego has: iStaminaPotion)
							(ego has: iGhostOil)
						)
						(messager say: N_ROOM NULL C_ALLFLASKSFULL 0 self)
					)
					(else
						(messager say: N_ROOM NULL C_NOFLASK 0 self)
					)
				)
			)
			(5
				(= seconds 2)
			)
			(6
				(if (ego has: iFlask)
					(= register TRUE)
					(ego use: iFlask 1)
				)
				(= ticks 60)
			)
			(7
				(ego setCycle: EndLoop self)
			)
			(8
				(if register
					(ego get: iFairyDust)
				)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh) (include "200.shm") (include "0.shm")
(use Main)
(use FPRoom)
(use Inset)
(use Scaler)
(use RangeOsc)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use StopWalk)
(use Motion)
(use Actor)
(use System)

(public
	rm200 0
)

(instance rm200 of FPRoom
	(properties
		noun N_ROOM
		picture 200
		east 210
	)
	
	(method (init)
		(switch prevRoomNum
			(east
				(= style SCROLLRIGHT)
				(theGame handsOn:)
			)
			(else 
				(= style PLAIN)
				(ego x: 185 y: 106)
				(theGame handsOn:)
			)
		)
		(ego init: setScale: Scaler 100 50 175 95 normalize:)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						10 87
						10 108
						81 134
						190 110
						206 136
						303 114
						249 84
						263 68
						319 82
						319 189
						0 189
						0 0
						319 0
						319 66
						255 50
						201 49
						134 50
						119 58
						73 61
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						62 80
						91 66
						145 76
						170 83
						133 95
					yourself:
				)
		)
		(if (not (Btst fSavedSrini))
			(LoadMany RES_SOUND 185 2184 2202)
			(theMusic1 number: 201 loop: -1 play:)
			(srini init: stopUpd: setScale: -1 ego approachVerbs: V_TALK V_DO)
		)
		(if (Btst fSavedSrini)
			(ladder
				init:
				stopUpd:
				ignoreActors:
				approachVerbs: V_LOOK V_DO
				setPri: 5
			)
		)
		(anthill init: approachVerbs: V_DO V_LADDER setOnMeCheck: ftrControl cBLUE)
		(rocks init: approachVerbs: V_DO setOnMeCheck: ftrControl cGREEN)
		(cacti init: setOnMeCheck: ftrControl cCYAN)
		(farCacti init: setOnMeCheck: ftrControl cLRED)
		(road init: approachVerbs: V_DO setOnMeCheck: ftrControl cRED)
		(bridge init: approachVerbs: V_DO setOnMeCheck: ftrControl cMAGENTA)
		(tracks init: approachVerbs: V_DO setOnMeCheck: ftrControl cBROWN)
		(cliff init: approachVerbs: V_DO setOnMeCheck: ftrControl cLGREY)
		(ants setCycle: Forward init: ignoreActors: setPri: 5)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego cYELLOW)
				(curRoom setScript: sEgoFalls)
			)
		)
	)
	
	(method (dispose)
		(theMusic1 fade:)
		(theMusic2 fade:)
		(super dispose:)
	)
)

(instance sWalkSrini of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(= cycles 5)
			)
			(1
				(sriniCU setMotion: MoveTo 115 87 self)
			)
			(2
				(sriniCU
					setCycle: RangeOsc (Random 2 5) (sriniCU cel?) (+ (sriniCU cel?) 2) self
				)
			)
			(3
				(sriniCU setCycle: Walk setMotion: MoveTo 115 97 self)
			)
			(4
				(sriniCU
					setCycle: RangeOsc (Random 2 5) (sriniCU cel?) (+ (sriniCU cel?) 2) self
				)
			)
			(5
				(sriniCU setCycle: Walk setMotion: MoveTo 115 107 self)
			)
			(6
				(sriniCU dispose:)
				(inSriniWalk dispose:)
				(theIconBar enable:)
			)
		)
	)
)

(instance sEgoFalls of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LoadMany RES_SOUND 2133 2134)
				(if (ego wearingGuns?)
					(Load RES_VIEW 134)
				else
					(Load RES_VIEW 133)
				)
				(ego setSpeed: 7 setHeading: 180)
				(= cycles 5)
			)
			(1
				(ego setMotion: MoveFwd 40 self)
			)
			(2
				(ego
					setScale: Scaler 100 50 175 95
					setPri: 15
					view: (if (ego wearingGuns?) 134 else 133)
					setCel: 0
					setSpeed: 12
					setCycle: EndLoop self
				)
				(localSound number: 2133 play: self)
			)
			(3 (ego hide:))
			(4
				(localSound number: 2134 play: self)
				(ShakeScreen 25 shakeSDiagonal)
				(= ticks 190)
			)
			(5 (ShakeScreen 5 shakeSDiagonal))
			(6 (= seconds 3))
			(7
				(= deathReason deathFALLOFFCLIFF)
				(curRoom newRoom: DEATH)
			)
		)
	)
)

(instance sTalkToSrini of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_SRINI V_TALK register 1 5 self)
			)
			(1
				(messager say: N_SRINI V_TALK register 6 10 self)
			)
			(2
				(srini cycleSpeed: 12 setCycle: EndLoop self)
			)
			(3 (srini setCycle: BegLoop self))
			(4
				(srini stopUpd: cycleSpeed: 6)
				(self dispose:)
			)
		)
	)
)

(instance sSaveSrini of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LoadMany RES_SOUND 2202 185 2184)
				((ScriptID tlkFreddy FREDDY) textY: 35 talkWidth: 140)
				((ScriptID tlkSrini SRINI)
					x: 207
					y: 81
					textX: -170
					textY: 35
					talkWidth: 140
				)
				(ego put: iLadder)
				(messager say: N_ANTHILL V_LADDER C_SRINI_TRAPPED 1 2 self)
			)
			(1 (theGame points: 10 0 self))
			(2
				(Bset fSavedSrini)
				(ladder
					init:
					stopUpd:
					ignoreActors:
					approachVerbs: V_LOOK V_DO
					setPri: 5
				)
				(localSound number: 2202 play:)
				(= cycles 30)
			)
			(3
				(ego setMotion: PolyPath 116 104 self)
				(srini loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(4 0)
			(5
				(Face ego srini)
				(= cycles 15)
			)
			(6
				(cast eachElementDo: #stopUpd)
				(cast eachElementDo: #ignoreActors)
				(= cycles 15)
			)
			(7
				(messager say: N_ANTHILL V_LADDER C_SRINI_TRAPPED 3 self)
			)
			(8
				(curRoom setInset: inSriniWalk self)
			)
			(9 (= cycles 30))
			(10
				(messager say: N_ANTHILL V_LADDER C_SRINI_TRAPPED 4 self)
			)
			(11
				(srini setMotion: MoveTo 109 61 self)
			)
			(12
				(srini setPri: -1 setMotion: MoveTo 110 70 self)
			)
			(13
				(ego setHeading: 270)
				(srini
					moveSpeed: 4
					cycleSpeed: 4
					setMotion: PolyPath 106 104 self
				)
			)
			(14
				(ego hide:)
				(srini
					view: 201
					loop: 3
					cel: 0
					x: 112
					y: 105
					moveSpeed: 12
					cycleSpeed: 12
					setCycle: CycleTo 8 1 self
				)
			)
			(15
				(messager say: N_ANTHILL V_LADDER C_SRINI_TRAPPED 5 7 self)
			)
			(16
				(srini setCycle: EndLoop)
				(= seconds 3)
			)
			(17
				(messager say: N_SRINI V_TALK C_SRINI_SAVED 0 self)
			)
			(18
				(theMusic1 fade:)
				(= ticks 120)
			)
			(19
				(cast eachElementDo: #hide)
				(curRoom style: FADEOUT drawPic: 780)
				(= ticks 120)
			)
			(20
				(messager say: N_DEMO_DONE NULL NULL 0 self 0)
			)
			(21 (= ticks 340))
			(22
				(theGame restart: TRUE)
				(self dispose:)
			)
		)
	)
)

(instance srini of Actor
	(properties
		x 138
		y 67
		noun N_SRINI
		approachX 156
		approachY 103
		view 201
		loop 1
		priority 5
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb &tmp theCase)
		(= theCase (if (Btst fSavedSrini) 1 else 3))
		(switch theVerb
			(V_LOOK
				(if (and (== theCase 3) (Btst 92))
					(= theCase 12)
					(messager say: noun theVerb theCase)
				else
					(messager say: noun theVerb theCase)
					(Bset 92)
				)
			)
			(4
				(messager say: noun theVerb theCase)
			)
			(2
				(if (Btst fSavedSrini)
					(messager say: N_SRINI V_TALK C_SRINI_SAVED)
				else
					(curRoom setScript: sTalkToSrini 0 theCase)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ants of Prop
	(properties
		x 136
		y 64
		z -2
		noun N_ANTS
		view 200
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: noun theVerb C_SRINI_TRAPPED)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ladder of View
	(properties
		x 119
		y 82
		z 17
		noun N_LADDER
		approachX 129
		approachY 101
		view 200
		loop 1
		priority 5
		signal (| ignrAct fixPriOn)
	)
)

(instance anthill of Feature
	(properties
		x 126
		y 81
		noun N_ANTHILL
		sightAngle 40
		approachX 105
		approachY 91
		approachDist 26
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LADDER
				(curRoom setScript: sSaveSrini)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rocks of Feature
	(properties
		x 80
		y 75
		noun N_ROCKS
		sightAngle 40
		approachX 73
		approachY 95
		approachDist 26
	)
)

(instance cacti of Feature
	(properties
		x 125
		y 23
		noun N_CACTI
		sightAngle 40
		approachX 161
		approachY 66
		approachDist 81
	)
)

(instance farCacti of Feature
	(properties
		x 125
		y 23
		noun N_FAR_CACTI
		sightAngle 40
		approachX 161
		approachY 66
		approachDist 81
	)
)

(instance road of Feature
	(properties
		x 125
		y 23
		noun N_ROAD
		sightAngle 40
		approachX 161
		approachY 66
		approachDist 81
	)
)

(instance bridge of Feature
	(properties
		x 289
		y 60
		noun N_BRIDGE
		sightAngle 40
		approachX 235
		approachY 84
		approachDist 65
	)
)

(instance tracks of Feature
	(properties
		x 140
		y 160
		noun N_TRACKS
		sightAngle 40
		approachX 81
		approachY 130
		approachDist 65
	)
)

(instance cliff of Feature
	(properties
		x 180
		y 160
		noun N_CLIFF
		sightAngle 40
		approachX 235
		approachY 84
		approachDist 65
	)
)

(instance inSriniWalk of Inset
	(properties
		view 205
		x 81
		y 32
		disposeNotOnMe TRUE
	)
	
	(method (init)
		(super init: &rest)
		(sriniCU
			init:
			setLoop: 1
			setPri: 15
			ignoreActors:
			setCycle: Walk
		)
		(theMusic1 stop:)
		(localSound number: 2184 play:)
		(self setScript: sWalkSrini)
	)
	
	(method (dispose)
		(super dispose:)
		(ants setCycle: Forward)
		(srini
			view: 801
			loop: 1
			x: 106
			y: 66
			setCycle: StopWalk -1
		)
		(localSound fade:)
		(theMusic1 number: 185 loop: -1 play:)
	)
)

(instance sriniCU of Actor
	(properties
		x 117
		y 77
		view 205
	)
)

(instance localSound of FPSound
	(properties
		flags mNOPAUSE
	)
)

(instance fallSound of FPSound
	(properties
		flags mNOPAUSE
	)
)

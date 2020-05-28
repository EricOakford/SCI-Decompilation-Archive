;;; Sierra Script 1.0 - (do not remove this comment)
(script# 305)
(include game.sh)
(use Main)
(use butte)
(use MoveToCoords)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Motion)
(use User)
(use System)

(public
	rm305 0
	pod 1
)

(instance rm305 of SQRoom
	(properties
		picture 305
		south 315
		west 300
	)
	
	(method (init)
		(if (Btst fPoliceArrive)
			((ScriptID BUTTE 4) init: view: 13 loop: 0 posn: 0 80)
		)
		(if
			(and
				(or
					(== (butte curPolice1Room?) 305)
					(== (butte curPolice2Room?) 305)
				)
				(or (< (butte policeLanded?) 0) (Btst fPoliceArrive))
			)
			((ScriptID BUTTE 4) posn: 0 80 init: forceUpd:)
		)
		(if
			(and
				(< (butte policeLanded?) 0)
				(or
					(== (butte curPolice1Room?) 305)
					(== (butte curPolice2Room?) 305)
				)
				(== (ego view?) 0)
			)
			(butte junctioned: TRUE)
			((ScriptID BUTTE 4) init: setScript: copEnters)
		else
			(butte junctioned: FALSE)
		)
		(LoadMany VIEW 0 305 300)
		(LoadMany SOUND 124 125)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						124 0 319 0 319 116 258 116 246 113 245 91
						165 102 147 90 145 80 122 72
					yourself:
				)
				((Polygon new:)
					type: PNearestAccess
					init: 0 0 112 0 112 71 60 70 0 66
					yourself:
				)
				((Polygon new:)
					type: PNearestAccess
					init:
						0 85 66 84 127 89 143 103 135 112 104 117 103
						130 90 132 86 151 59 178 62 189 0 189
					yourself:
				)
				((Polygon new:)
					type: PNearestAccess
					init: 319 189 186 189 187 165 170 152 165 125 250 125 254 122 319 123
					yourself:
				)
		)
		(if (> (butte policeLanded?) 0)
			(Load PICTURE 300)
		else
			(LoadMany VIEW 7 5)
		)
		(self setRegions: BUTTE)
		(theStairs init:)
		(theRoom init:)
		(HandsOn)
		(switch prevRoomNum
			(south
				(ego init:)
				(self style: 14)
			)
			(west
				(self style: 12)
				(ego
					init:
					y:
						(cond 
							((< (ego y?) 67) 67)
							((> (ego y?) 88) 88)
							(else (ego y?))
						)
					setHeading: 90 self
				)
			)
			(else 
				(HandsOff)
				(music number: 18 loop: -1 vol: 127 flags: mNOPAUSE playBed:)
				(if (Btst fTimeTravel)
					(self setScript: egoLands)
				else
					(ego
						view: 378
						loop: 0
						cel: 0
						x: 218
						y: 97
						setPri: 7
						normal: 0
						moveHead: 0
						init:
					)
					(self setScript: egoExits)
				)
			)
		)
		(ShowStatus 10)
		(super init:)
		(if (or (== prevRoomNum 300) (== prevRoomNum 315))
			(ego setPri: 8 ignoreActors: 0 illegalBits: 0)
			(pod loop: 1 cel: 10 init: stopUpd:)
			(door init: setPri: 5)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script 0)
			((== (ego edgeHit?) SOUTH) (HandsOff) (self setScript: exitSouthScript))
		)
		(super doit:)
		(= temp0 (ego onControl: origin))
		(cond 
			(script)
			((== (ego edgeHit?) SOUTH) (HandsOff) (self setScript: exitSouthScript))
			(
				(and
					(butte junctioned?)
					(not ((ScriptID BUTTE 4) script?))
				)
				(cond 
					((== (butte oldPoliceRoom?) 300) ((ScriptID BUTTE 4) posn: -5 75))
					((== (butte oldPoliceRoom?) 315) ((ScriptID BUTTE 4) posn: 150 250))
				)
				((ScriptID BUTTE 4) init:)
				((ScriptID BUTTE 4) setScript: copEnters)
			)
			(
				(or
					(& temp0 cBLUE)
					(& temp0 cGREEN)
					(& temp0 cRED)
					(& temp0 cLGREY)
					(& temp0 cGREY)
					(& temp0 cLBLUE)
				)
				(HandsOff)
				(self setScript: fallScript 0 temp0)
			)
			((and (& temp0 cYELLOW) (== (ego view?) 0)) (ego view: 0))
			(
				(and
					(!= script exitSouthScript)
					(not (& temp0 cYELLOW))
					(== (ego view?) 0)
				)
				(ego view: 0)
			)
		)
		(cond 
			(script)
			((and (< (ego y?) 96) (> (ego x?) 142)) (ego setPri: 5))
			((> (ego y?) 113) (ego setPri: 15))
			((> (ego y?) 86) (ego setPri: 13))
			(else (ego setPri: 8))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== newRoomNumber 531) (music stop:))
		(if
		(and (== script fallScript) (== (ego edgeHit?) 3))
			(= newRoomNumber 0)
		else
			(super newRoom: newRoomNumber)
		)
	)
)

(instance fallScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(cond 
					((& register cGREY) (ego setPri: 3))
					((& register cLBLUE) (ego x: (+ (ego x?) 10) setPri: 4))
					((& register cRED) (ego x: (- (ego x?) 10) setPri: 4))
					((and (& register cRED) (> (ego x?) 170)) (ego setPri: 6))
					(else (ego setPri: 4))
				)
				(curRoom setScript: (ScriptID BUTTE 1))
			)
		)
	)
)

(instance exitSouthScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(ego setHeading: (+ 180 (/ (- (ego x?) 210) 3)) self)
			)
			(1
				(= temp0
					(+ (CelHigh (ego view?) (ego loop?) (ego cel?)) 1)
				)
				(ego setMotion: MoveToY (+ 189 temp0) self)
			)
			(2
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance egoLands of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(podFlash init: setCycle: Forward)
				(= cycles 1)
			)
			(1
				(globalSound number: 838 vol: 127 loop: 1 play:)
				(= cycles 24)
			)
			(2
				(pod init: setCycle: CycleTo 9 1 self)
			)
			(3
				(globalSound fade:)
				(door init: setPri: (+ (pod priority?) 1))
				(pod setCycle: EndLoop self)
			)
			(4
				(globalSound stop:)
				(podFlash dispose:)
				(= cycles 3)
			)
			(5
				(HandsOn)
				(curRoom newRoom: 531)
			)
		)
	)
)

(instance egoExits of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== state 1) (== (globalSound prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door init:)
				(pod setCel: 10 init:)
				(if (Btst fPoliceArrive) ((ScriptID BUTTE 4) init:))
				(= cycles 1)
			)
			(1
				(globalSound number: 124 loop: 1 vol: 127 play:)
			)
			(2
				(globalSound number: 142 play:)
				(door setCycle: EndLoop)
				(= cycles 1)
			)
			(3 (door setCycle: EndLoop self))
			(4
				(globalSound stop:)
				(ego setCycle: EndLoop self)
			)
			(5
				(pod priority: -1 stopUpd:)
				(ego view: 0 loop: 1 normal: 1 moveHead: 1 x: 221 y: 97)
				(NormalEgo)
				(= cycles 3)
			)
			(6
				(globalSound number: 142 play:)
				(door setPri: 3 setCycle: BegLoop self)
			)
			(7
				(globalSound stop:)
				(HandsOn)
				(User canControl: TRUE)
				(client setScript: 0)
			)
		)
	)
)

(instance enterPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 221 97 self)
				(= cycles 10)
			)
			(1
				(if (cast contains: (ScriptID BUTTE 4))
					(ego setMotion: 0)
					(Face ego (ScriptID BUTTE 4))
					((ScriptID BUTTE 4) setScript: (ScriptID BUTTE 5))
					(self dispose:)
				else
					(ego setMotion: PolyPath 221 97 self)
				)
			)
			(2
				(if (cast contains: (ScriptID BUTTE 4))
					(ego setMotion: 0)
					(Face ego (ScriptID BUTTE 4))
					((ScriptID BUTTE 4) setScript: (ScriptID BUTTE 5))
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(3
				(globalSound number: 142 play:)
				(door setCycle: EndLoop self)
			)
			(4
				(ego
					view: 378
					normal: 0
					loop: 1
					cel: 0
					x: 224
					y: 75
					illegalBits: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(5
				(door setPri: (+ (ego priority?) 1))
				(globalSound number: 142 loop: 1 vol: 127 play:)
				(door cel: 9 init: setCycle: BegLoop self)
			)
			(6
				(globalSound number: 125 play:)
				(ego dispose:)
				(HandsOn)
				(= cycles 2)
			)
			(7
				(if (< (butte policeLanded?) 0) (Bset fPoliceArrive))
				(curRoom newRoom: 531)
			)
		)
	)
)

(instance copEnters of Script
	(properties)
	
	(method (doit)
		(if (and (not (curRoom script?)) (== state 0))
			(self cue:)
		)
		(if
			(and
				(< ((ScriptID BUTTE 4) distanceTo: ego) 80)
				(not (curRoom script?))
			)
			((ScriptID BUTTE 4) setScript: (ScriptID BUTTE 5))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				((ScriptID BUTTE 4)
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 146 95 self
				)
			)
			(2
				((ScriptID BUTTE 4)
					setMotion: MoveTo (ego x?) (ego y?) self
				)
			)
		)
	)
)

(instance podFlash of Sq4Prop
	(properties
		x 186
		y 57
		view 305
		priority 6
		signal (| ignrAct fixPriOn)
	)
)

(instance pod of Sq4Prop
	(properties
		x 254
		y 85
		view 305
		loop 1
		priority 5
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 1))
			(V_DO
				(HandsOff)
				(curRoom setScript: enterPod)
			)
			(V_SMELL (narrator say: 2))
			(V_TASTE (narrator say: 3))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance door of Sq4Prop
	(properties
		x 194
		y 61
		view 305
		loop 2
		priority 10
		signal fixPriOn
	)
)

(instance theStairs of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck $4000
		lookStr 4
	)
)

(instance theRoom of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				((ScriptID BUTTE 6) doVerb: theVerb)
			)
			(V_LOOK (narrator say: 5))
			(else  (super doVerb:))
		)
	)
)

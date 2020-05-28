;;; Sierra Script 1.0 - (do not remove this comment)
(script# 395)
(include game.sh)
(use Main)
(use mall)
(use BeltWay)
(use SQRoom)
(use Sq4Dialog)
(use Sq4Narrator)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use StopWalk)
(use Wander)
(use Motion)
(use User)
(use System)

(public
	rm395 0
)

(local
	[local0 2]
	timesTalkedToCrowd
	local3
	atATM
	atmState
	oldSortedFeatures
	local7
	local8 =  245
	local9 =  27
	local10 =  1
	local11
)
(procedure (CrowdInteractions theVerb &tmp ret)
	(= ret 1)
	(switch theVerb
		(V_TALK
			(if (not (curRoom script?))
				(if (ego inRect: 192 64 239 96)
					(curRoom setScript: talkCrowd)
				else
					(narrator say: 12)
				)
			)
		)
		(V_SMELL (narrator say: 13))
		(V_TASTE (narrator say: 14))
		(else  (= ret 0))
	)
	(return ret)
)

(instance rm395 of SQRoom
	(properties
		picture 395
		style FADEOUT
		north 390
		east 400
		south 400
		west 390
	)
	
	(method (init &tmp theX theY)
		(cond 
			((not (Btst fBeenAtSoftwareExcess))
				(freak1 init:)
				(freak2 init:)
				(freak3 init:)
				(freak4 init:)
				(freak5 init:)
				(freak6 init:)
				(freak7 init:)
				(freak8 init:)
				(freak1 setScript: freaksComeOut)
				(Bset fBeenAtSoftwareExcess)
				(Bset fFreaksAtSoftwareExcess)
			)
			((Btst fFreaksAtSoftwareExcess)
				(freak1 init:)
				(freak2 init:)
				(freak3 init:)
				(freak4 init:)
				(freak5 init:)
				(freak6 init:)
				(freak7 init:)
				(freak8 init:)
				(freak1 x: 225 y: 64 stopUpd:)
				(freak5 x: 239 y: 72 stopUpd:)
			)
		)
		(atm init:)
		(letters init:)
		(bush1 init:)
		(bush2 init:)
		(zapper init:)
		(zapper2 init:)
		(b1 init: setStep: 1 1 setMotion: Wander)
		(b2 init: setStep: 1 1 setMotion: Wander)
		(b7 init: setStep: 1 1 setMotion: Wander)
		(if (== (theGame detailLevel:) 0)
			(b1 setMotion: 0 stopUpd:)
			(b2 setMotion: 0 stopUpd:)
			(b7 setMotion: 0 stopUpd:)
		)
		(cond 
			((and (!= prevRoomNum 397) (not (Btst fSoftwareExcessClosed))) (globalSound number: 59 loop: -1 flags: 1 play: 95))
			((Btst fSoftwareExcessClosed) (globalSound number: 0 vol: 0 stop:))
		)
		(if
		(and (!= prevRoomNum 397) (!= heldBox 0))
			(Bset fSoftwareExcessClosed)
		)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(HandsOff)
		(switch prevRoomNum
			(west
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= theX -12)
					(= theY 81)
				else
					(= theX -18)
					(= theY -2)
				)
				(ego
					x: theX
					y: theY
					setLoop: theStopGroop
					illegalBits: cWHITE
				)
				(self setScript: (ScriptID MALL 1) 0 egoBwGreen)
			)
			(east
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= theX 232)
					(= theY 245)
				else
					(= theX 331)
					(= theY 173)
				)
				(ego
					x: theX
					y: theY
					setLoop: theStopGroop
					illegalBits: cWHITE
				)
				(self setScript: (ScriptID MALL 1) 0 egoBwBlue)
			)
			(396
				(music setVol: 95)
				(= atmState 1)
				(ego view: 396)
				(useCard start: 9)
				(curRoom setScript: useCard)
				(useCard start: 0)
			)
			(397
				(music number: 405 loop: -1 flags: 1 play: 95)
				(self setScript: fromStoreScript)
			)
			(else 
				(if (== ((ScriptID MALL 0) whichBelt?) 1)
					(= theX 232)
					(= theY 245)
				else
					(= theX 331)
					(= theY 173)
				)
				(ego
					x: theX
					y: theY
					setLoop: theStopGroop
					illegalBits: cWHITE
				)
				(self setScript: (ScriptID MALL 1) 0 egoBwBlue)
			)
		)
		(if (!= prevRoomNum 396)
			(ego
				view: (if (== prevRoomNum 396) 396 else (ego view?))
				illegalBits: 0
			)
		)
		(if (== prevRoomNum 396) (ego view: 396))
		(ego
			setPri: -1
			code: beltwayCode
			init:
			setCycle: SyncWalk
		)
		(super init:)
		(if (Btst fSoftwareExcessClosed)
			(addToPics add: door eachElementDo: #init doit:)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 0 129 120 189 0 189
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 0 35 142 110 105 132 0 78
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 294 189 221 189 154 154 188 132
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							14 0 231 0 319 43 319 164 203 106 213 94
							183 78 175 88 173 82 111 55 100 54 112 45
							91 33 76 34
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init: 0 129 120 189 0 189
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 0 35 142 110 105 132 0 78
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 294 189 221 189 154 154 188 132
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 14 0 247 0 235 54 170 89 99 52 108 45 89 35 76 34
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init: 319 171 199 101 319 7
						yourself:
					)
			)
		)
		(store init:)
		(self setRegions: MALL)
		(= local11 14)
	)
	
	(method (doit &tmp temp0 temp1)
		(if (not (-- local10))
			(cond 
				(
					(<
						(= temp0 (GetDistance (ego x?) (ego y?) local8 local9))
						0
					)
					(= temp0 0)
				)
				((> temp0 300) (= temp0 300))
			)
			(globalSound setVol: (- 127 (/ temp0 3)))
			(= local10 60)
		)
		(cond 
			(script 0)
			(
			(and (ego edgeHit?) (OneOf (ego edgeHit?) EAST SOUTH WEST))
				(cond 
					((OneOf (ego edgeHit?) EAST SOUTH)
						(HandsOff)
						((ScriptID MALL 0) enterBelt: egoBwGreen)
						(self setScript: (ScriptID MALL 2) 0 east)
					)
					((== (ego edgeHit?) WEST)
						(HandsOff)
						((ScriptID MALL 0) enterBelt: egoBwBlue)
						(self setScript: (ScriptID MALL 2) 0 west)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 804)
		(DisposeScript WANDER)
		(= useSortedFeatures oldSortedFeatures)
		(super dispose:)
	)
)

(instance beltwayCode of Code
	(properties)
	
	(method (doit &tmp temp0 theControl)
		(cond 
			((curRoom script?) 0)
			((& (ego onControl:) cYELLOW)
				(if (not atATM)
					(= atATM TRUE)
					(proc700_5 1)
					(ego setMotion: MoveTo 105 45)
				)
			)
			((& (= theControl (ego onControl: origin)) local11)
				(cond 
					((& theControl cCYAN)
						(if (cast contains: freak1)
							(if (!= (ego illegalBits?) cWHITE)
								(ego illegalBits: cWHITE)
							)
						else
							(curRoom newRoom: 397)
						)
					)
					((& theControl cGREEN)
						(egoBwGreen who: ego doit:)
						((ScriptID MALL 0) whichBelt: 1)
						(proc700_5 0)
						(music fade: 127 10 5 0)
					)
					((& theControl cBLUE)
						(= atATM FALSE)
						(egoBwBlue who: ego doit:)
						((ScriptID MALL 0) whichBelt: 2)
						(proc700_5 0)
						(music fade: 95 10 5 0)
					)
				)
			)
			((| (egoBwGreen onCon?) (egoBwBlue onCon?))
				(egoBwGreen onCon: 0)
				(egoBwBlue onCon: 0)
				(ego xStep: 3 yStep: 2 setPri: -1 illegalBits: -32704)
				(proc700_5 1)
			)
		)
	)
)

(instance useCard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sq4 setCursor: theCursor TRUE 304 172)
				(ego setMotion: MoveTo 105 45 self)
			)
			(1 (= cycles 3))
			(2
				(cond 
					((== (ego view?) 374) (= atmState 1))
					((or (== (ego view?) 4) (== (ego view?) 0)) (= atmState 0))
					(else (= atmState 2))
				)
				(ego setHeading: 45)
				(= cycles 2)
			)
			(3
				(ego
					view: 396
					loop:
					(switch atmState
						(0 0)
						(1 2)
						(2 1)
					)
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(4
				(flash
					init:
					posn: 116 22
					loop: 9
					cycleSpeed: 48
					cel: 0
					setCycle: EndLoop self
				)
			)
			(5
				(flash loop: 7 cycleSpeed: 6 setCycle: Forward)
				(= seconds 3)
			)
			(6
				(if (!= atmState 1) (flash loop: 8))
				(= seconds 3)
			)
			(7
				(flash dispose:)
				(Animate (cast elements?) 0)
				(if (== atmState 1)
					(SolvePuzzle fATMCardAccepted 10)
					(= cycles 2)
				else
					(Bset fATMCardDeclined)
					(= state 9)
					(narrator modNum: 395 say: 1 self)
				)
			)
			(8
				(if (== atmState 1)
					(music setVol: 30)
					(curRoom newRoom: 396)
				else
					(= cycles 2)
				)
			)
			(9
				(ego
					view: 396
					setLoop: 2
					setCel: 255
					x: 105
					y: 45
					setHeading: 45
					illegalBits: cWHITE
					init:
				)
				(= cycles 1)
			)
			(10 (ego setCycle: BegLoop self))
			(11
				(ego setSpeed: speed loop: 6)
				(switch atmState
					(0 (NormalEgo 6 0))
					(1
						(ego
							view: 373
							setCycle: StopWalk 374
							setLoop: -1
							setLoop: theStopGroop
						)
						(EgoHeadMove 374)
					)
					(2
						(ego
							view: 402
							setCycle: StopWalk 14
							setLoop: -1
							setLoop: theStopGroop
						)
						(EgoHeadMove 14)
					)
				)
				(= cycles 1)
			)
			(12 (HandsOn) (self dispose:))
		)
	)
)

(class rssScript of Script
	(properties)
	
	(method (doit &tmp temp0 temp1)
		(super doit:)
		(if local3
			(if (= temp0 ((User curEvent?) type?))
				(Display 395 0 p_restore local3)
				(Event dispose:)
				(= local3 0)
				(= seconds 0)
				(= cycles 1)
			)
			(= temp0 0)
		)
	)
)

(instance talkCrowd of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(tROG say: 1 self)
			)
			(1
				(switch timesTalkedToCrowd
					(0
						(tAlien say: 1 self)
						(= timesTalkedToCrowd 1)
					)
					(1
						(= timesTalkedToCrowd 2)
						(tAlien say: 2 self)
					)
					(2
						(tAlien say: 3 self)
						(= timesTalkedToCrowd 3)
					)
					(3 (narrator say: 4 self))
				)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance freaksComeOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 1))
			(1
				(HandsOff)
				(freak1
					ignoreActors: TRUE
					setCycle: Walk
					setStep: 4 3
					setMotion: PolyPath 225 64
				)
				(freak5
					ignoreActors: TRUE
					setCycle: Walk
					setStep: 4 3
					setMotion: PolyPath 239 72 self
				)
			)
			(2 (= cycles 10))
			(3
				(freak1 stopUpd: ignoreActors: 0)
				(freak5 stopUpd: ignoreActors: 0)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance fromStoreScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 theRegister)
		(switch (= state newState)
			(0
				(LoadMany VIEW 993 992 26)
				(HandsOff)
				(ego
					x: 233
					y: 61
					illegalBits: 0
					setMotion: PolyPath 209 85 self
				)
			)
			(1
				(if (or (not heldBox) (Btst fBoughtHintbook))
					(HandsOn)
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(2
				(zap init: posn: 208 100 setCycle: Forward)
				(= cycles 5)
			)
			(3
				(globalSound number: 838 play:)
				(cond 
					((OneOf (ego view?) 373 374) (= register 993) (= temp0 0))
					((OneOf (ego view?) 402 14) (= register 992) (= temp0 0))
					((OneOf (ego view?) 0 4) (= register 26) (= temp0 1))
				)
				(ego view: register cel: 0 setLoop: temp0)
				(= cycles 2)
			)
			(4
				(switch register
					(993
						(= theRegister register)
						(= temp1 1)
					)
					(992
						(= theRegister register)
						(= temp1 1)
					)
					(26
						(= theRegister 395)
						(= temp1 5)
					)
				)
				(ego view: theRegister setLoop: temp1 setCycle: Forward)
				(= seconds 5)
			)
			(5
				(globalSound stop: -1)
				(zap dispose:)
				(ego view: register cel: 0 setLoop: temp0)
				(= cycles 1)
			)
			(6 (ego setCycle: EndLoop self))
			(7 (EgoDead iconDEAD deathSHOPLIFTING) (= cycles 1))
			(8 (HandsOn) (self dispose:))
		)
	)
)

(instance egoBwGreen of BeltWay
	(properties
		xStep 2
		yStep -1
		xOff 1
		yOff -1
		xTweak 1
		yTweak -1
		key 315
		head 119
		xDir 1
		yDir -1
	)
)

(instance egoBwBlue of BeltWay
	(properties
		xStep 2
		yStep -1
		xOff 1
		yOff -1
		xTweak 1
		yTweak -1
		key 135
		head 301
		xDir -1
		yDir 1
	)
)

(instance door of Sq4View
	(properties
		x 217
		y 90
		view 395
		loop 1
		priority 3
		signal (| ignrAct fixPriOn)
	)
)

(instance b1 of Sq4Actor
	(properties
		x 295
		y 104
		view 395
		priority 1
		signal (| fixedLoop skipCheck fixPriOn)
		lookStr 5
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_TASTE)
			(if (== (++ local7) 18) (SQ4Print 395 1))
			(super doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (setSpeed)
	)
)

(instance b2 of Sq4Actor
	(properties
		x 272
		y 91
		view 395
		cel 1
		priority 1
		signal (| fixedLoop skipCheck fixPriOn)
		lookStr 5
	)
	
	(method (setSpeed)
	)
)

(instance b3 of Sq4Actor
	(properties
		x 156
		y 27
		view 395
		cel 2
		priority 1
		signal (| fixedLoop skipCheck fixPriOn)
		lookStr 5
	)
	
	(method (setSpeed)
	)
)

(instance b4 of Sq4Actor
	(properties
		x 165
		y 40
		view 395
		cel 3
		priority 1
		signal (| fixedLoop skipCheck fixPriOn)
		lookStr 5
	)
	
	(method (setSpeed)
	)
)

(instance b5 of Sq4Actor
	(properties
		x 272
		y 91
		view 395
		cel 3
		priority 1
		signal (| fixedLoop skipCheck fixPriOn)
	)
	
	(method (setSpeed)
	)
)

(instance b6 of Sq4Actor
	(properties
		x 160
		y 34
		view 395
		cel 1
		priority 1
		signal (| fixedLoop skipCheck fixPriOn)
	)
	
	(method (setSpeed)
	)
)

(instance b7 of Sq4Actor
	(properties
		x 165
		y 34
		view 395
		priority 1
		signal (| fixedLoop skipCheck fixPriOn)
		lookStr 5
	)
	
	(method (setSpeed)
	)
)

(instance zap of Sq4Prop
	(properties
		view 395
		loop 4
	)
)

(instance letters of Sq4Feature
	(properties
		x 198
		y 16
		z 8
		nsLeft 193
		nsBottom 17
		nsRight 204
		sightAngle 90
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (SQ4Print 395 2))
		)
	)
)

(instance atm of Sq4Feature
	(properties
		x 110
		y 19
		nsLeft 102
		nsBottom 39
		nsRight 129
		sightAngle 90
		lookStr 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (not (& (ego onControl: origin) cYELLOW))
					(narrator say: 7)
				else
					(narrator say: 8)
				)
			)
			(V_ATMCARD
				(if (not (& (ego onControl: origin) cYELLOW))
					(narrator say: 7)
				else
					(curRoom setScript: useCard)
				)
			)
			(V_SMELL (narrator say: 9))
			(V_TASTE (narrator say: 10))
			(else  (super doVerb: theVerb))
		)
	)
)

(class Crowd of Sq4View
	(properties
		lookStr 11
	)
	
	(method (doVerb theVerb)
		(if (not (CrowdInteractions theVerb))
			(super doVerb: theVerb)
		)
	)
)

(instance freak1 of Sq4Actor
	(properties
		x 156
		y 117
		view 382
		illegalBits $0000
		lookStr 11
	)
	
	(method (doVerb theVerb)
		(if (self inRect: 185 25 259 67)
			(if (not (CrowdInteractions theVerb))
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance freak2 of Crowd
	(properties
		x 219
		y 54
		view 388
	)
)

(instance freak3 of Crowd
	(properties
		x 254
		y 69
		view 384
	)
)

(instance freak4 of Crowd
	(properties
		x 263
		y 76
		view 401
		loop 3
	)
)

(instance freak5 of Sq4Actor
	(properties
		x 125
		y 133
		view 384
		illegalBits $0000
		lookStr 11
	)
	
	(method (doVerb theVerb)
		(if (self inRect: 185 25 259 67)
			(if (not (CrowdInteractions theVerb))
				(super doVerb: theVerb)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance freak6 of Crowd
	(properties
		x 202
		y 57
		view 7
		cel 1
	)
)

(instance freak7 of Crowd
	(properties
		x 244
		y 77
		view 395
		loop 6
	)
)

(instance freak8 of Crowd
	(properties
		x 200
		y 60
		view 395
		loop 6
		cel 1
	)
)

(instance flash of Sq4Prop
	(properties
		view 395
		loop 7
	)
)

(instance store of Sq4Feature
	(properties
		x 280
		y 30
		lookStr 15
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 16))
			(V_TASTE (narrator say: 17))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return
			(>=
				-51
				(/ (* 100 (- 154 (event y?))) (- (event x?) 320))
			)
		)
	)
)

(instance bush1 of Sq4Feature
	(properties
		x 52
		y 60
		nsTop 41
		nsLeft 46
		nsBottom 69
		nsRight 66
		lookStr 18
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL (narrator say: 18))
			(V_TASTE (narrator say: 18))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bush2 of Sq4Feature
	(properties
		x 245
		y 150
		nsTop 140
		nsLeft 238
		nsBottom 167
		nsRight 261
		lookStr 18
	)
	
	(method (doVerb theVerb)
		(bush1 doVerb: theVerb)
	)
)

(instance zapper of Sq4Feature
	(properties
		x 179
		y 58
		nsTop 57
		nsLeft 177
		nsBottom 60
		nsRight 181
		sightAngle 90
		lookStr 19
	)
)

(instance zapper2 of Sq4Feature
	(properties
		x 239
		y 89
		nsTop 88
		nsLeft 238
		nsBottom 91
		nsRight 240
		sightAngle 90
		lookStr 19
	)
)

(instance tROG of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1008
		talkerNum ROGER
		mouthOffsetX 21
		mouthOffsetY 32
		eyeOffsetX 27
		eyeOffsetY 21
	)
)

(instance tAlien of Sq4Talker
	(properties
		z 400
		noun ALIEN
		view 1368
		talkerNum ALIEN
		eyeLoop -1
		mouthOffsetX 21
		mouthOffsetY 12
	)
)

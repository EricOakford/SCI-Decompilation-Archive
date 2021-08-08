;;; Sierra Script 1.0 - (do not remove this comment)
(script# 683)
(include game.sh)
(use Main)
(use castle)
(use KQ5Room)
(use Sync)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	cdCassimaToon 0
)

(local
	saveEgoView
	saveCycleSpeed
	saveMoveSpeed
)
(instance cdCassimaToon of KQ5Room
	(properties
		picture 57
	)
	
	(method (init)
		(self setRegions: 550)
		(Load PICTURE 96)
		(Load VIEW 690)
		(Load VIEW 686)
		(Load VIEW 688)
		(Load VIEW 860)
		(Load VIEW 1036)
		(Load SCRIPT 929)
		(Load SOUND 103)
		(Load RES_SYNC 1078)
		(Load RES_SYNC 1079)
		(Load RES_SYNC 1080)
		(Load RES_SYNC 1081)
		(Load RES_SYNC 1082)
		(Load RES_SYNC 1068)
		(Load RES_SYNC 1069)
		(Load RES_SYNC 1070)
		(Load RES_SYNC 1071)
		(ego init: yStep: 3)
		((ego head?)
			x: (ego x?)
			y: (ego y?)
			z: (CelHigh (ego view?) (ego loop?) (ego cel?))
			moveHead: TRUE
			show:
		)
		(princess
			setPri: 11
			setLoop: 0
			setCycle: Forward
			cycleSpeed: 3
			posn: 70 152
			init:
			ignoreActors: TRUE
		)
		(fireplace
			cycleSpeed: 8
			setCycle: (if (> (theGame detailLevel:) 1) Forward else 0)
			init:
		)
		(self style: WIPERIGHT)
		(super init:)
		(= saveMoveSpeed (theGame egoMoveSpeed?))
		(theGame egoMoveSpeed: 1)
		(self setScript: giveLocketScript)
	)
	
	(method (dispose)
		(theGame egoMoveSpeed: saveMoveSpeed)
		(super dispose: &rest)
	)
)

(instance goBack of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(princess setLoop: 1 cel: 0 setCycle: EndLoop)
			)
		)
	)
)

(instance giveLocketScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveCycleSpeed (ego cycleSpeed?))
				(= saveEgoView (ego view?))
				(theMusic number: 103 loop: -1 playBed:)
				(if (!= (princess loop?) 1)
					(princess cycleSpeed: 1 setLoop: 1 cel: 0 setCycle: EndLoop)
				)
				(ego cycleSpeed: 2 setMotion: PolyPath 98 153 self)
			)
			(1
				((ego head?) hide:)
				(egoBody init: posn: (ego x?) (ego y?))
				(CastleEgoSpeed)
				(ego
					normal: 0
					view: 690
					posn: (+ (ego x?) 2) (- (ego y?) 37)
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(ego cel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(3
				(princess
					cycleSpeed: 2
					setLoop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(princess setLoop: 3 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(5
				(ego cel: 0 setLoop: 2 setCycle: EndLoop self)
				(princess setCel: 255)
			)
			(6
				(princess setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(7
				(ego
					normal: 1
					view: saveEgoView
					cycleSpeed: saveCycleSpeed
					setCycle: KQ5SyncWalk
					setStep: 3 2
					setLoop: 5
				)
				((ego head?) show:)
				(= cycles 1)
			)
			(8
				((ego head?) hide:)
				(ego hide:)
				(egoBody hide:)
				(princess hide:)
				(fireplace hide:)
				(curRoom drawPic: 96 FADEOUT)
				(hisEyes init: setCel: 0 setScript: boyBlinkScript)
				(theMouth init:)
				(cassimaEyes init:)
				(egoFace init:)
				(locket init: setScript: locketScript)
				(= cycles 1)
			)
			(9
				(CastleHandsOff)
				(= seconds 2)
			)
			(10
				(cassimaEyes
					setPri: (+ (theMouth priority?) 1)
					setScript: girlBlinkScript
				)
				(theMouth setCycle: MouthSync 1078)
				(SpeakAudio 1078 self)
			)
			(11
				(cassimaLips init:)
				(egoFace hide:)
				(theMouth changeMouth: 1 setCycle: MouthSync 1079)
				(SpeakAudio 1079 self)
			)
			(12
				(egoFace show:)
				(cassimaLips hide:)
				(theMouth changeMouth: 0 setCycle: MouthSync 1080)
				(SpeakAudio 1080 self)
			)
			(13
				(cls)
				(= seconds 2)
				(theMouth setCel: 4)
			)
			(14
				(theMouth setCycle: MouthSync 1081)
				(SpeakAudio 1081 self)
			)
			(15
				(cls)
				(= cycles 1)
				(theMouth setCel: 4)
			)
			(16
				(theMouth setCycle: MouthSync 1082)
				(SpeakAudio 1082 self)
			)
			(17
				(cls)
				(cassimaLips show:)
				(= seconds 1)
			)
			(18
				(hisEyes dispose:)
				(cassimaEyes dispose:)
				(cassimaLips dispose:)
				(egoFace dispose:)
				(locket dispose:)
				(theMouth hide:)
				(curRoom drawPic: 57 10)
				(ego posn: 112 153 setLoop: 1 setCel: 1 show:)
				(princess setLoop: 4 setCel: 255 posn: 80 151 show:)
				((ego head?)
					setCel: 5
					moveHead: 0
					setScript: egoHeadMove
					show:
				)
				(fireplace show:)
				(CastleHandsOff)
				(= cycles 2)
			)
			(19
				(princess
					setLoop: 5
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(20 (SpeakAudio 1068 self))
			(21
				((ego head?) setScript: 0)
				(theMouth show: changeMouth: 2 setCycle: MouthSync 1069)
				(SpeakAudio 1069 self)
			)
			(22
				(theMouth hide:)
				((ego head?) setScript: egoHeadMove)
				(SpeakAudio 1070 self)
			)
			(23
				((ego head?) setScript: 0)
				(theMouth show: setCycle: MouthSync 1071)
				(SpeakAudio 1071 self)
				(theMusic fade:)
			)
			(24
				(theMouth setCycle: 0 dispose:)
				(cls)
				(CastleHandsOn)
				(Bclr 64)
				(theMusic number: 101 loop: -1 play:)
				(= seconds 2)
			)
			(25
				(curRoom newRoom: 57)
				(client setScript: 0)
			)
		)
	)
)

(instance egoHeadMove of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) loop: (Random 4 6))
				(-- state)
				(= cycles 3)
			)
		)
	)
)

(instance kingScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(egoFace setCel: (Random 0 5))
				(= cycles (Random 1 4))
			)
			(1 (self init:))
		)
	)
)

(instance girlBlinkScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(cassimaEyes setLoop: 1 cycleSpeed: 0 setCycle: EndLoop self)
			)
			(1 (= cycles (Random 1 4)))
			(2
				(cassimaEyes setCycle: BegLoop self)
			)
			(3 (= seconds (Random 1 3)))
			(4 (self init:))
		)
	)
)

(instance boyBlinkScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hisEyes setLoop: 5 cycleSpeed: 0 setCycle: EndLoop self)
			)
			(1 (= cycles (Random 1 4)))
			(2 (hisEyes setCycle: BegLoop self))
			(3 (= seconds (Random 3 9)))
			(4 (self init:))
		)
	)
)

(instance locketScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(locket
					setCycle: Forward
					setLoop: (Random 3 4)
					cycleSpeed: (Random 1 3)
				)
				(= seconds (Random 1 3))
			)
			(1
				(locket setLoop: 4 setCel: 255)
				(= seconds (Random 2 6))
			)
			(2 (self init:))
		)
	)
)

(instance egoBody of Prop
	(properties
		view 690
		loop 3
		signal (| ignrAct ignrHrz fixedLoop)
	)
)

(instance fireplace of Prop
	(properties
		x 134
		y 131
		view 686
	)
)

(instance princess of Actor
	(properties
		view 688
	)
)

(instance cassimaLips of Prop
	(properties
		x 91
		y 95
		view 1036
		loop 1
		cel 4
	)
)

(instance cassimaEyes of Prop
	(properties
		x 117
		y 75
		view 860
		loop 1
	)
)

(instance hisEyes of Prop
	(properties
		x 201
		y 53
		view 860
		loop 5
	)
)

(instance theMouth of Prop
	(properties
		x 91
		y 95
		view 1036
		loop 1
		cel 4
	)
	
	(method (changeMouth param1)
		(switch param1
			(0
				(theMouth view: 1036 loop: 1 cel: 4 x: 91 y: 95)
			)
			(1
				(theMouth view: 860 loop: 2 x: 216 y: 76)
			)
			(2
				(theMouth
					view: 688
					loop: 7
					signal: 26624
					x: (+ (princess x?) 1)
					y: (princess y?)
					z: 34
				)
			)
		)
	)
)

(instance egoFace of Prop
	(properties
		x 216
		y 76
		view 860
		loop 2
	)
)

(instance locket of Prop
	(properties
		x 148
		y 89
		view 860
		loop 4
	)
)

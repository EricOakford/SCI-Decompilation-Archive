;;; Sierra Script 1.0 - (do not remove this comment)
(script# 670)
(include game.sh)
(use Main)
(use AudioScript)
(use KQ5Room)
(use RandCyc)
(use LoadMany)
(use Motion)
(use Actor)

(public
	cdEnding 0
)

(local
	saveSpeed
)
(instance cdEnding of KQ5Room
	(properties
		picture 65
	)
	
	(method (init &tmp temp0)
		(Load PICTURE 65)
		(Load SCRIPT 941)
		(Load SCRIPT 929)
		(LoadMany VIEW 916 2 922 933 929 918 935 720 0)
		(cond 
			((== prevRoomNum 672)
				(self setScript: cartoon3)
			)
			((== prevRoomNum 671)
				(Load VIEW 925)
				(LoadMany RES_SYNC 5206 5208)
				(self setScript: cartoon2)
			)
			(else
				(if (!= prevRoomNum 124)
					(ego posn: 164 140)
				)
				(LoadMany RES_SYNC 1083 1084 1085 1086 1087 5201)
				(= saveSpeed (theGame egoMoveSpeed?))
				(theGame egoMoveSpeed: 1)
				(self style: 3)
				(ego
					normal: 1
					view: 2
					setPri: -1
					setLoop: -1
					cycleSpeed: 1
					moveSpeed: 1
					loop: 1
					setStep: 3 2
					init:
				)
				((ego head?) moveHead: 0 show:)
				(theGame setSpeed: 3)
				(self setScript: cartoon)
			)
		)
		(coals setCycle: RandCycle init:)
		(super init:)
		(HandsOff)
		(theGame setCursor: invCursor TRUE)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(theGame egoMoveSpeed: saveSpeed)
		(super dispose:)
	)
)

(instance cartoon of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cedric init:)
				(if (and (!= prevRoomNum 671) (!= prevRoomNum 672))
					((ScriptID 763) doit:)
				)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo 87 177 self)
			)
			(2
				(theMouth init: hide: play: 10121 671)
				(= waitForCue 8192)
			)
			(3
				(if (== howFast 2)
					(ego
						normal: 0
						view: 916
						setLoop: 0
						ignoreActors: TRUE
						cycleSpeed: (if (== howFast 2) 1 else 0)
						moveSpeed: 1
						setCycle: EndLoop self
					)
				else
					(ego
						normal: 0
						view: 916
						setLoop: 0
						ignoreActors: TRUE
						cycleSpeed: (if (== howFast 2) 1 else 0)
						moveSpeed: 1
					)
					(ego setCel: (ego lastCel:))
					(= cycles 1)
				)
				((ego head?) hide:)
			)
			(4 (= waitForCue 8448))
			(5
				(egoMagic
					init:
					cycleSpeed: (if (== howFast 2) 1 else 0)
					setCycle: EndLoop self
				)
			)
			(6
				(egoMagic dispose:)
				(ego setCycle: BegLoop self)
			)
			(7 (= waitForCue 8704))
			(8
				(ego setLoop: 2 setCycle: EndLoop self)
			)
			(9
				(ego normal: 1 view: 2 setLoop: -1)
				((ego head?) show:)
				(egoWand init: setCycle: EndLoop self)
			)
			(10
				(egoWand stopUpd:)
				(cassima init:)
				(= seconds 2)
			)
			(11
				(cassima setCycle: EndLoop self)
			)
			(12 (= seconds 2))
			(13
				(cassima
					setLoop: 1
					setCycle: Walk
					cycleSpeed: 5
					setStep: 1 1
					moveSpeed: 2
					setMotion: MoveTo 52 169
				)
				(= waitForCue 8784)
			)
			(14
				(Face ego cassima 5)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 916
					cycleSpeed: 4
					setStep: 3 2
					setLoop: 4
				)
				(= waitForCue 8960)
			)
			(15
				(ego setCycle: RandCycle)
				(= waitForCue 9216)
			)
			(16
				(cassima setLoop: 2)
				(ego setCycle: 0 setCel: 255)
				(= cycles 1)
			)
			(17 (= waitForCue 9472))
			(18
				(cassima setCycle: RandCycle)
				(theMouth show: changeMouth: 0)
				(= waitForCue 9728)
			)
			(19
				(cassimaHead init: setPri: 15 cel: 0 show:)
				(theMouth hide:)
				(cassima setCycle: 0 setCel: 255)
				(= cycles 1)
			)
			(20 (= cycles 1))
			(21 (= waitForCue 9984))
			(22
				(ego setCycle: RandCycle)
				(= waitForCue 10240)
			)
			(23
				(ego setCycle: 0 setCel: 0)
				(cassima setLoop: 3 setCycle: RandCycle)
				(cassimaHead x: 53)
				(theMouth x: 53 show:)
				(cassimaHead hide:)
				(= waitForCue 10496)
			)
			(24
				(cassimaHead setCycle: 0 cel: 0 show:)
				(theMouth hide:)
				(cassima setCycle: 0 setCel: 0)
				(= cycles 1)
			)
			(25 (= waitForCue 10752))
			(26
				(cassima cycleSpeed: 2)
				(crispin
					cel: 0
					cycleSpeed: (if (== howFast 2) 2 else 0)
					moveSpeed: 2
					setLoop: 0
					setCycle: EndLoop self
					init:
				)
			)
			(27 (= waitForCue 10832))
			(28
				(crispin view: 922 setLoop: 1 cel: 0)
				(theMouth changeMouth: 2 show:)
				(= waitForCue 11008)
			)
			(29
				(ego setCycle: 0 setCel: 0)
				(= waitForCue 11264)
			)
			(30
				(theMouth setCel: 0)
				(curRoom newRoom: 671)
			)
		)
	)
)

(instance cartoon2 of AudioScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cedric init:)
				(cassima init: setLoop: 2 setCel: 0 posn: 52 169)
				(crispin view: 922 setLoop: 1 init:)
				(crispin cel: (- (NumCels crispin) 1))
				(crispinMouth init:)
				(ego
					normal: 1
					view: 2
					setStep: 2 1
					setLoop: -1
					posn: 87 177
					init:
				)
				((ego head?) show:)
				(Face ego cassima 5)
				(= seconds 2)
			)
			(1
				(theMouth init: hide: play: 10123 672)
				(= waitForCue 13056)
			)
			(2
				(if (== howFast 2)
					(crispin setLoop: 1 setCycle: EndLoop self)
				else
					(crispin setLoop: 1)
					(crispin setCel: (crispin lastCel:))
					(= cycles 1)
				)
			)
			(3 (= waitForCue 13312))
			(4
				(theMouth show: changeMouth: 2)
				(= waitForCue 13568)
			)
			(5
				(crispinMouth hide: setCycle: 0)
				(theMouth hide:)
				(crispin setLoop: 2 setCycle: EndLoop)
				(= waitForCue 13824)
			)
			(6
				(cMagic
					init:
					setPri: (+ (crispin priority?) 1)
					cycleSpeed: (if (== howFast 2) 2 else 0)
					setCycle: EndLoop self
				)
			)
			(7 (= waitForCue 14080))
			(8
				(cMagic
					setLoop: 4
					posn: 118 165
					setCycle: EndLoop
					cycleSpeed: (if (== howFast 2) 3 else 0)
				)
				(= waitForCue 14336)
			)
			(9
				(alexander init: cycleSpeed: 0 setCycle: EndLoop self)
				(Face ego alexander 5)
			)
			(10 (= waitForCue 14592))
			(11
				(cassima setLoop: 6 setCel: 0)
				(cMagic
					setStep: 3 3
					posn: 118 165
					cel: 0
					setMotion: MoveTo (- (cMagic x?) 3) (- (cMagic y?) 3)
					cycleSpeed: (if (== howFast 2) 3 else 0)
					setCycle: EndLoop
				)
				(= waitForCue 14848)
			)
			(12
				(rosella init: cycleSpeed: 0 setCycle: EndLoop self)
			)
			(13 (= waitForCue 15104))
			(14
				(rosella cel: 0 setLoop: 3 setCycle: EndLoop)
				(cMagic
					setStep: 6 6
					posn: 118 165
					cel: 0
					setMotion: MoveTo (- (cMagic x?) 8) (- (cMagic y?) 8)
					setCycle: EndLoop
					cycleSpeed: (if (== howFast 2) 3 else 0)
				)
				(= waitForCue 15360)
			)
			(15
				(cMagic dispose:)
				(valanice init: cycleSpeed: 0 setCycle: EndLoop self)
				(Face ego rosella 5)
			)
			(16
				(crispin setCycle: BegLoop self)
			)
			(17
				(crispin view: 922 setLoop: 7 setCycle: EndLoop self)
			)
			(18
				(valanice cel: 0 setLoop: 1 setCycle: EndLoop)
				(= seconds 2)
			)
			(19
				(if (== howFast 2)
					(valanice setCycle: BegLoop)
					(rosella setCycle: BegLoop)
				)
				(= waitForCue 15616)
			)
			(20
				(valanice setLoop: 0)
				(alexander setLoop: 0)
				(rosella setLoop: 2)
				(valanice cel: (- (NumCels valanice) 1))
				(alexander cel: (- (NumCels alexander) 1))
				(rosella cel: (- (NumCels rosella) 1))
				(= waitForCue 15872)
			)
			(21
				((ego head?) setCel: 0)
				(ego
					illegalBits: 0
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					setMotion: MoveTo (- (valanice x?) 6) (+ (valanice y?) 1) self
					moveSpeed: (if (== howFast 2) 2 else 0)
				)
			)
			(22
				(valanice hide:)
				((ego head?) hide: setCel: -1)
				(ego
					view: 916
					normal: 0
					setLoop: 5
					cycleSpeed: 2
					moveSpeed: (if (== howFast 2) 2 else 0)
					setCycle: EndLoop self
				)
			)
			(23 (= seconds 2))
			(24 (ego setCycle: BegLoop self))
			(25
				(valanice show:)
				((ego head?) show:)
				(ego
					normal: 1
					setLoop: -1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					moveSpeed: 0
					view: 2
					setMotion: MoveTo (- (rosella x?) 3) (+ (rosella y?) 3) self
				)
			)
			(26 (= waitForCue 16128))
			(27
				(= waitForCue 16384)
				(alexander hide:)
				(rosella hide:)
				((ego head?) hide:)
				(ego
					view: 916
					cel: 0
					normal: 0
					setLoop: 6
					cycleSpeed: (if (== howFast 2) 3 else 1)
					setCycle: EndLoop
				)
			)
			(28 (ego setCycle: BegLoop self))
			(29
				(alexander show:)
				(valanice show:)
				(rosella show:)
				((ego head?) show:)
				(ego
					view: 2
					setLoop: -1
					normal: 1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				(if (== howFast 2) (= seconds 2) else (= cycles 1))
			)
			(30
				(ego
					setMotion: MoveTo (- (valanice x?) 5) (+ (valanice y?) 4) self
				)
			)
			(31
				(ego setMotion: MoveTo 96 139 self)
			)
			(32
				((ego head?) moveHead: 0)
				(ego
					view: 2
					normal: 1
					cycleSpeed: 0
					setLoop: 2
					setCycle: KQ5SyncWalk
					setLoop: -1
				)
				(= waitForCue 16640)
			)
			(33
				((ego head?) hide:)
				(ego
					view: 916
					normal: 0
					setLoop: 7
					cel: 0
					setCycle: EndLoop self
				)
			)
			(34
				(= waitForCue 16896)
				(ego cycleSpeed: 2 setLoop: 10 setCycle: Forward)
			)
			(35 (ego setCycle: BegLoop self))
			(36
				(ego setLoop: 7)
				(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
			)
			(37
				(ego
					view: 2
					normal: 1
					cycleSpeed: 0
					setLoop: 2
					setCycle: KQ5SyncWalk
					setLoop: -1
				)
				((ego head?) show:)
				(= cycles 1)
			)
			(38
				(cassima
					ignoreActors: 1
					setLoop: 1
					cycleSpeed: 2
					moveSpeed: 2
					setCycle: Forward
					setMotion: MoveTo 91 169 self
				)
			)
			(39
				(cassima
					setCycle: Forward
					setLoop: 6
					setMotion: MoveTo 97 150 self
				)
			)
			(40 (curRoom newRoom: 672))
		)
	)
)

(instance cartoon3 of AudioScript

	(method (changeState newState)
		(switch (= state newState)
			(0
				(UnLoad 129 218)
				(LoadMany 128 928 925)
				(Load 142 10125)
				(cedric init:)
				(valanice
					init:
					posn: 106 134
					cel: (- (NumCels valanice) 1)
				)
				(rosella
					init:
					posn: 116 135
					cel: (- (NumCels rosella) 1)
				)
				(alexander
					init:
					posn: 127 135
					cel: (- (NumCels alexander) 1)
				)
				(cassima
					init:
					setLoop: 2
					cel: 1
					signal: 16384
					illegalBits: 0
					posn: 71 135
				)
				(crispin view: 922 setLoop: 7 init:)
				(crispin cel: (- (NumCels crispin) 1))
				(ego view: 2 posn: 94 135 ignoreActors: 1 loop: 2 init:)
				(= seconds 3)
			)
			(1
				(alexander
					view: 928
					setLoop: 3
					setPri: 15
					setCycle: Walk
					moveSpeed: 2
					cycleSpeed: 2
					setMotion: MoveTo 117 144 self
				)
			)
			(2
				(alexander
					setLoop: 1
					setMotion: MoveTo (+ (cassima x?) 20) (+ (cassima y?) 1) self
				)
			)
			(3 (= seconds 2))
			(4
				(cassima hide:)
				(alexander
					view: 929
					posn: (+ (cassima x?) 18) (+ (cassima y?) 1)
					setLoop: 4
					cycleSpeed: 3
					moveSpeed: 3
					setCycle: EndLoop self
				)
			)
			(5
				(theMouth init: hide: play: 10125 673)
				(alexander setLoop: 5 setCycle: EndLoop self)
			)
			(6
				(alexander setCycle: BegLoop self)
			)
			(7
				(alexander setLoop: 4)
				(alexander
					cel: (- (NumCels alexander) 1)
					setCycle: BegLoop self
				)
			)
			(8
				(alexander
					posn: (+ (cassima x?) 20) (+ (cassima y?) 1)
				)
				(= cycles 1)
			)
			(9
				(cassima show:)
				(alexander
					view: 928
					ignoreActors: TRUE
					setCycle: Forward
					setLoop: 1
					setMotion: MoveTo (- (alexander x?) 5) (alexander y?) self
				)
			)
			(10
				(alexander setLoop: 5 setMotion: MoveTo 83 136 self)
			)
			(11
				(alexHead init:)
				(alexander hide:)
				(valanice hide:)
				(rosella hide:)
				(cassima view: 933 loop: 7 cel: 0 show:)
				(ego setCel: 0)
				(ego
					view: 933
					setLoop: 0
					posn: 98 136
					ignoreActors: 1
					normal: 0
				)
				((ego head?) view: 933 setLoop: 13 hide:)
				(crispin
					setLoop: 9
					setPri: (+ (ego priority?) 1)
					cycleSpeed: (if (== howFast 2) 4 else 2)
				)
				(theMouth show: changeMouth: 3)
				(crispin hide:)
				(= waitForCue 6144)
			)
			(12
				(crispin cycleSpeed: 0 show: setLoop: 7)
				(theMouth hide:)
				(crispin
					cel: (- (NumCels crispin) 1)
					setCycle: BegLoop self
				)
			)
			(13
				(crispin setLoop: 1)
				(crispin cel: (- (NumCels crispin) 1))
				(= waitForCue 6400)
			)
			(14
				(theMouth changeMouth: 2 show:)
				(= waitForCue 6656)
			)
			(15
				(theMouth hide:)
				(cMagic
					posn: 63 180
					setLoop: 3
					cycleSpeed: (if (== howFast 2) 2 else 0)
					cel: 0
					setCycle: EndLoop
					init:
				)
				(= waitForCue 6912)
			)
			(16
				(cMagic dispose:)
				(bottle
					init:
					ignoreActors: 1
					cycleSpeed: (if (== howFast 2) 2 else 0)
					setCycle: EndLoop self
				)
			)
			(17
				(bottle view: 922 stopUpd:)
				(crispin
					setLoop: 7
					cycleSpeed: (if (== howFast 2) 2 else 0)
					setCycle: EndLoop self
				)
			)
			(18 (= waitForCue 7168))
			(19
				(crispin setLoop: 9 cycleSpeed: 4)
				(crispin setCycle: RandCycle)
				(= waitForCue 7424)
			)
			(20
				(crispin setCycle: BegLoop self)
			)
			(21 (= waitForCue 7680))
			(22
				(theMouth show: changeMouth: 5)
				(crispin setLoop: 8 setCycle: EndLoop)
				(= waitForCue 7936)
			)
			(23 (= waitForCue 8448))
			(24
				(theMouth cel: 0)
				(crispin setCycle: BegLoop self)
			)
			(25 (= waitForCue 8704))
			(26
				(crispin setLoop: 9)
				(crispin setCycle: RandCycle)
				(= waitForCue 8960)
			)
			(27
				(crispin setCycle: BegLoop self)
			)
			(28
				(ego setLoop: 8)
				(if (== howFast 2)
					(egoTemp
						init:
						view: 916
						setLoop: 8
						posn: 93 136
						setCycle: EndLoop
					)
				else
					(egoTemp init: view: 916 setLoop: 8 posn: 93 136)
					(egoTemp cel: (egoTemp lastCel:))
				)
				(= cycles 1)
			)
			(29 (= waitForCue 9216))
			(30 (= waitForCue 9472))
			(31
				(if (== howFast 2)
					(egoTemp setCycle: BegLoop self)
				else
					(egoTemp cel: 0)
					(= cycles 1)
				)
			)
			(32
				(theMouth hide:)
				(egoTemp dispose:)
				(ego setLoop: 0 show:)
				(crispin setLoop: 9)
				(= waitForCue 9728)
			)
			(33
				(alexander dispose:)
				(valanice dispose:)
				(rosella dispose:)
				(crispin setCycle: RandCycle)
				(= waitForCue 9984)
			)
			(34 (= waitForCue 10240))
			(35
				(crispin setCycle: BegLoop self)
			)
			(36
				(crispin setLoop: 8 setCycle: EndLoop self)
			)
			(37 (= waitForCue 10320))
			(38
				(crispin setCycle: BegLoop)
				(cedric cel: 0 setLoop: 8 setCycle: EndLoop self)
			)
			(39
				(cedric setLoop: 5 cycleSpeed: 2 setCycle: Forward)
				(= seconds 3)
			)
			(40
				(cedric setLoop: 6 setCycle: EndLoop self)
			)
			(41 (= waitForCue 10496))
			(42
				(theMouth changeMouth: 7 show:)
				(= waitForCue 10752)
			)
			(43
				(theMouth hide:)
				(crispin setLoop: 9)
				(crispin setCycle: RandCycle)
				(= waitForCue 11008)
			)
			(44
				(crispin setCycle: 0)
				(theMouth show:)
				(= waitForCue 11264)
			)
			(45
				(theMouth hide:)
				(crispin setCycle: RandCycle)
				(= waitForCue 11344)
			)
			(46
				(crispin setCycle: BegLoop self)
			)
			(47 (= waitForCue 11520))
			(48
				(crispin setLoop: 9)
				(crispin setCycle: RandCycle)
				(= waitForCue 11776)
				(theMouth changeMouth: 1 show:)
			)
			(49
				(crispin setCycle: BegLoop self)
			)
			(50 (= waitForCue 12032))
			(51 (= waitForCue 12288))
			(52
				(theMouth changeMouth: 8)
				(alexHead hide:)
				(= waitForCue 12544)
			)
			(53
				(alexHead setLoop: 12 show: setCycle: BegLoop)
				(theMouth hide:)
				(crispin setLoop: 8 cel: 0 setCycle: EndLoop self)
			)
			(54
				(theMouth changeMouth: 6 show:)
				(= waitForCue 12800)
			)
			(55
				(crispin setCycle: BegLoop)
				(cassima view: 933 setLoop: 7 setCycle: EndLoop self)
			)
			(56
				(cassima dispose:)
				(= waitForCue 13056)
			)
			(57 (= waitForCue 13312))
			(58
				(theMouth hide:)
				(crispin setLoop: 9)
				(crispin setCycle: RandCycle)
				(= waitForCue 13568)
			)
			(59
				(theMouth changeMouth: 7 show:)
				(crispin setCycle: BegLoop self)
			)
			(60 (= waitForCue 13824))
			(61
				(theMouth hide:)
				(crispin
					setLoop: 9
					setPri: (+ (ego priority?) 1)
					cycleSpeed: 4
				)
				(crispin setCycle: RandCycle)
				(= waitForCue 14080)
			)
			(62 (= waitForCue 14336))
			(63
				(crispin setLoop: 8 cel: 0 setCycle: EndLoop self)
			)
			(64 (= waitForCue 14416))
			(65
				(alexHead dispose:)
				(ego cycleSpeed: 2 setLoop: 0 setCycle: EndLoop self)
			)
			(66
				(ego setLoop: 1)
				(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
			)
			(67 (ego hide:) (= seconds 2))
			(68
				(if (> (DoAudio 6) -1) (-- state))
				(= cycles 1)
			)
			(69 (curRoom newRoom: 673))
		)
	)
)

(instance egoMagic of Prop
	(properties
		x 87
		y 167
		view 916
		loop 1
	)
)

(instance egoWand of Prop
	(properties
		x 98
		y 176
		view 916
		loop 3
	)
)

(instance cassima of Actor
	(properties
		x 6
		y 148
		view 918
	)
)

(instance crispin of Actor
	(properties
		x 68
		y 163
		view 935
	)
)

(instance crispinMouth of Prop
	(properties
		view 922
		loop 10
		signal ignrAct
	)
	
	(method (doit)
		(super doit:)
		(self x: (+ (crispin x?) 3) y: (- (crispin y?) 23))
		(self priority: (+ (crispin priority?) 1))
	)
)

(instance cMagic of Actor
	(properties
		x 63
		y 180
		view 935
		loop 3
	)
)

(instance alexander of Actor
	(properties
		x 127
		y 138
		view 929
	)
)

(instance valanice of Actor
	(properties
		x 108
		y 138
		view 925
	)
)

(instance rosella of Actor
	(properties
		x 116
		y 138
		view 925
		loop 2
	)
)

(instance bottle of Prop
	(properties
		x 81
		y 187
		view 935
		loop 6
		priority 15
		signal fixPriOn
	)
)

(instance cedric of Actor
	(properties
		x 232
		y 152
		view 720
		loop 2
		cel 2
		signal ignrAct
		illegalBits $0000
	)
)

(instance cassimaHead of Prop
	(properties
		x 52
		y 145
		view 918
		loop 5
		cycleSpeed 2
	)
)

(instance cedricHead of Prop
	(properties
		x 229
		y 144
		view 720
		loop 7
		cycleSpeed 2
	)
	
	(method (init)
		(super init:)
		(self signal: ignrAct)
	)
)

(instance egoTemp of Prop)

(instance egoHead of Prop
	(properties
		x 93
		y 110
		view 916
		loop 9
		cycleSpeed 3
	)
	
	(method (init)
		(super init:)
		(self signal: (| ignrAct fixPriOn) setPri: 10)
	)
)

(instance coals of Prop
	(properties
		x 281
		y 152
		view 933
		loop 15
		priority 12
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
		detailLevel 3
	)
)

(instance theMouth of MonoAudioProp
	(properties
		x 53
		y 145
		view 918
		loop 5
		priority 15
	)
	
	(method (changeMouth param1)
		(switch param1
			(0
				(theMouth view: 918 loop: 5 x: 52 y: 145 setPri: 15)
			)
			(1
				(theMouth view: 933 loop: 10 posn: 72 111 setPri: 12)
			)
			(2
				(theMouth
					view: 922
					loop: 10
					posn: (+ (crispin x?) 3) (- (crispin y?) 23)
					setPri: (+ (crispin priority?) 1)
				)
			)
			(3
				(theMouth
					view: 922
					setLoop: 9
					setPri: (+ (ego priority?) 1)
					posn: 68 163
				)
			)
			(4
				(theMouth
					view: 916
					setLoop: 7
					posn: (ego x?) (ego y?)
					setPri: (ego priority?)
				)
			)
			(5
				(theMouth
					view: 916
					loop: 9
					setPri: 10
					cel: 0
					signal: (| ignrAct fixPriOn)
					posn: 93 110
				)
			)
			(6
				(theMouth
					view: 916
					loop: 9
					cel: 0
					setPri: 10
					posn: 93 110
				)
			)
			(7
				(theMouth
					view: 720
					loop: 7
					x: 229
					y: 144
					signal: ignrAct
				)
			)
			(8
				(theMouth view: 933 x: 81 y: 110 setLoop: 13)
			)
		)
	)
)

(instance alexHead of Prop
	(properties
		x 81
		y 110
		view 933
		loop 12
		cycleSpeed 3
	)
)

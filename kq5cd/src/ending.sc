;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include sci.sh)
(use Main)
(use KQ5Room)
(use Sync)
(use RandCyc)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	ending 0
)

(instance ending of KQ5Room
	(properties
		picture 65
	)
	
	(method (init &tmp temp0)
		(Load rsSCRIPT 941)
		(cond 
			((== prevRoomNum 122) (self setScript: cartoon3))
			((== prevRoomNum 121) (LoadMany 128 929 925 918 2) (self setScript: cartoon2))
			(else
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
				((ego head?) show:)
				(theGame setSpeed: 3)
				((ScriptID 763) doit:)
				(self setScript: cartoon)
			)
		)
		(coals setCycle: RandCycle init:)
		(super init:)
		(HandsOff)
		(theGame setCursor: invCursor 1)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(super dispose:)
	)
)

(instance cartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 15)
				(cedric init:)
			)
			(1
				(ego setMotion: MoveTo 87 177 self)
			)
			(2
				(ego
					normal: 0
					view: 916
					setLoop: 0
					ignoreActors: 1
					cycleSpeed: 1
					moveSpeed: 1
					setCycle: EndLoop self
				)
				((ego head?) hide:)
			)
			(3
				(theAudio number: 8138 loop: 1 play:)
				(egoMagic init: cycleSpeed: 1 setCycle: EndLoop self)
			)
			(4
				(egoMagic dispose:)
				(ego setCycle: BegLoop self)
			)
			(5 (= seconds 2))
			(6 (SpeakAudio 1083 self))
			(7
				(ego setLoop: 2 setCycle: EndLoop self)
			)
			(8
				(cls)
				(ego normal: 1 view: 2 setLoop: -1)
				((ego head?) show:)
				(theAudio number: 8139 loop: 1 play:)
				(egoWand init: setCycle: EndLoop self)
			)
			(9
				(egoWand stopUpd:)
				(theMusic number: 141 loop: 1 play:)
				(cassima init:)
				(= seconds 2)
			)
			(10
				(cassima setCycle: EndLoop self)
			)
			(11 (= seconds 2))
			(12
				(cassima
					setLoop: 1
					setCycle: Walk
					cycleSpeed: 5
					setStep: 2 1
					moveSpeed: 2
					setMotion: MoveTo 52 169 self
				)
			)
			(13
				(if
					(and
						(not (== howFast 0))
						(< (theMusic prevSignal?) 10)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(14
				(cassima setCycle: 0)
				(Face ego cassima 5)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 916
					cycleSpeed: 4
					setStep: 3 2
					setLoop: 4
				)
				(= cycles 1)
			)
			(15
				(ego setCycle: MouthSync 1084)
				(SpeakAudio 1084 self)
			)
			(16
				(cls)
				(ego setCycle: EndLoop)
				(cassima setLoop: 2 cycleSpeed: 5 setCycle: RandCycle)
				(cassimaHead init: setPri: 15)
				(= cycles 1)
			)
			(17
				(if
					(and
						(not (== howFast 0))
						(< (theMusic prevSignal?) 20)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(18
				(cassimaHead setCycle: MouthSync 1085)
				(SpeakAudio 1085 self)
			)
			(19
				(cassimaHead setCycle: 0 cel: 0)
				(cassima setCycle: EndLoop self)
			)
			(20 (cls) (= cycles 1))
			(21
				(if
					(and
						(not (== howFast 0))
						(< (theMusic prevSignal?) 30)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(22
				(ego setCycle: MouthSync 1086)
				(SpeakAudio 1086 self)
			)
			(23
				(cls)
				(ego setCycle: BegLoop)
				(cassima setLoop: 3 setCycle: RandCycle)
				(cassimaHead setCycle: MouthSync 1087)
				(SpeakAudio 1087 self)
			)
			(24
				(cassimaHead setCycle: 0 cel: 0)
				(cassima setCycle: BegLoop self)
			)
			(25
				(Load rsVIEW 922)
				(if (not (== (theMusic prevSignal?) -1)) (-- state))
				(= cycles 1)
			)
			(26
				(cassima cycleSpeed: 2)
				(cls)
				(theAudio number: 8134 loop: 1 play:)
				(crispin
					cel: 0
					cycleSpeed: 2
					moveSpeed: 2
					setLoop: 0
					setCycle: EndLoop self
					init:
				)
			)
			(27
				(theMusic number: 142 loop: -1 play:)
				(cls)
				(crispin view: 922 setLoop: 1 cel: 0)
				(ego setCycle: RandCycle)
				(SpeakAudio 5200 self)
			)
			(28
				(cls)
				(ego setCycle: BegLoop)
				(crispinMouth init: cycleSpeed: 2)
				(crispinMouth setCycle: MouthSync 5201)
				(SpeakAudio 5201 self)
			)
			(29
				(cls)
				(crispinMouth setCycle: 0)
				(curRoom newRoom: 121)
			)
		)
	)
)

(instance cartoon2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cedric init:)
				(cassima init: setLoop: 1 posn: 52 169)
				(cassima cel: (- (NumCels cassima) 1))
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
				(= cycles 15)
			)
			(1
				(theMusic number: 143 loop: -1 play:)
				(crispin setLoop: 1 setCycle: EndLoop self)
			)
			(2
				(crispinMouth setCycle: MouthSync 5206)
				(SpeakAudio 5206 self)
			)
			(3
				(cls)
				(crispinMouth hide: setCycle: 0)
				(crispin setLoop: 2 setCycle: EndLoop self)
			)
			(4
				(theAudio number: 8138 loop: 1 play:)
				(cMagic
					init:
					setPri: (+ (crispin priority?) 1)
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(5
				(theAudio number: 8138 loop: 1 play:)
				(cMagic setLoop: 4 posn: 118 165 setCycle: EndLoop self)
			)
			(6
				(theAudio number: 8140 loop: 1 play:)
				(alexander init: setCycle: EndLoop self)
				(Face ego alexander 5)
			)
			(7
				(theAudio number: 8138 loop: 1 play:)
				(cMagic
					setStep: 3 3
					posn: 118 165
					cel: 0
					setMotion: MoveTo (- (cMagic x?) 3) (- (cMagic y?) 3)
					setCycle: EndLoop self
				)
			)
			(8
				(theAudio number: 8140 loop: 1 play:)
				(rosella init: setCycle: EndLoop self)
			)
			(9
				(rosella cel: 0 setLoop: 3 setCycle: EndLoop)
				(theAudio number: 8138 loop: 1 play:)
				(cMagic
					setStep: 6 6
					posn: 118 165
					cel: 0
					setMotion: MoveTo (- (cMagic x?) 8) (- (cMagic y?) 8)
					setCycle: EndLoop self
				)
			)
			(10
				(cMagic dispose:)
				(theAudio number: 8140 loop: 1 play:)
				(valanice init: setCycle: EndLoop self)
				(Face ego rosella 5)
			)
			(11
				(crispin setCycle: BegLoop self)
			)
			(12
				(crispin view: 922 setLoop: 7 setCycle: EndLoop self)
			)
			(13
				(valanice cel: 0 setLoop: 1 setCycle: EndLoop)
				(= seconds 2)
			)
			(14
				(theMusic fade:)
				(valanice setCycle: BegLoop)
				(rosella setCycle: BegLoop)
				(= seconds 2)
			)
			(15
				(theMusic number: 144 loop: -1 play:)
				(valanice setLoop: 0)
				(alexander setLoop: 0)
				(rosella setLoop: 2)
				(valanice cel: (- (NumCels valanice) 1))
				(alexander cel: (- (NumCels alexander) 1))
				(rosella cel: (- (NumCels rosella) 1))
				(cls)
				(SpeakAudio 5207 self)
			)
			(16
				((ego head?) setCel: 0)
				(ego
					illegalBits: 0
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					setMotion: MoveTo (- (valanice x?) 6) (+ (valanice y?) 1) self
				)
			)
			(17 (= seconds 2))
			(18
				(valanice hide:)
				((ego head?) hide: setCel: -1)
				(ego
					view: 916
					normal: 0
					setLoop: 5
					cycleSpeed: 2
					moveSpeed: 2
					setCycle: EndLoop self
				)
			)
			(19 (= seconds 2))
			(20 (ego setCycle: BegLoop self))
			(21 (= seconds 2))
			(22
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
			(23
				(cls)
				(SpeakAudio 5208 self)
				(alexander hide:)
				(rosella hide:)
				((ego head?) hide:)
				(ego
					view: 916
					cel: 0
					normal: 0
					setLoop: 6
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(24 (= seconds 5))
			(25 (ego setCycle: BegLoop self))
			(26
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
				(= seconds 2)
			)
			(27
				(ego
					setMotion: MoveTo (- (valanice x?) 5) (+ (valanice y?) 4) self
				)
			)
			(28
				(ego setMotion: MoveTo 96 136 self)
			)
			(29
				((ego head?) hide:)
				(ego view: 916 normal: 0 setLoop: 7 setCycle: EndLoop self)
			)
			(30
				(ego setCycle: MouthSync 5209)
				(SpeakAudio 5209 self)
				(ego cycleSpeed: 2 setLoop: 10 setCycle: Forward)
			)
			(31 (ego setCycle: BegLoop self))
			(32
				(ego
					setLoop: 7
					cel: (- (NumCels ego) 1)
					setCycle: BegLoop self
				)
			)
			(33
				((ego head?) show:)
				(ego
					view: 2
					normal: 1
					cycleSpeed: 0
					setLoop: 2
					setCycle: KQ5SyncWalk
					setLoop: -1
				)
				(= cycles 1)
			)
			(34
				(cassima
					ignoreActors: 1
					setLoop: 1
					cycleSpeed: 2
					moveSpeed: 2
					setCycle: Forward
					setMotion: MoveTo 91 169 self
				)
			)
			(35
				(cassima
					setCycle: Forward
					setLoop: 6
					setMotion: MoveTo 97 150 self
				)
			)
			(36
				(cls)
				(curRoom newRoom: 122)
			)
		)
	)
)

(instance cartoon3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
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
					setLoop: 1
					cel: 5
					signal: 16384
					illegalBits: 0
					posn: 71 135
				)
				(crispin view: 922 setLoop: 7 init:)
				(crispin cel: (- (NumCels crispin) 1))
				(ego view: 2 posn: 94 135 ignoreActors: 1 loop: 2 init:)
				(= cycles 15)
			)
			(1
				(cls)
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
					ignoreActors: 1
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
				(UnLoad 128 928)
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
					cycleSpeed: 4
				)
				(theMusic stop:)
				(theMusic2 number: 145 loop: 1 play:)
				(crispin setCycle: MouthSync 5212)
				(SpeakAudio 5212 self)
			)
			(12
				(crispin setLoop: 7)
				(crispin
					cel: (- (NumCels crispin) 1)
					setCycle: BegLoop self
				)
			)
			(13
				(crispin setLoop: 1)
				(crispin cel: (- (NumCels crispin) 1))
				(= cycles 1)
			)
			(14
				(cls)
				(crispinMouth init: cycleSpeed: 2)
				(crispinMouth setCycle: MouthSync 5213)
				(SpeakAudio 5213 self)
			)
			(15
				(cls)
				(crispinMouth setCycle: 0 hide:)
				(theAudio number: 8138 loop: 1 play:)
				(cMagic
					posn: 63 180
					setLoop: 3
					cycleSpeed: 2
					cel: 0
					setCycle: EndLoop self
					init:
				)
			)
			(16
				(cMagic dispose:)
				(theAudio number: 8140 loop: 1 play:)
				(bottle
					init:
					ignoreActors: 1
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(17
				(bottle view: 922 stopUpd:)
				(theMusic number: 146 loop: 1 play:)
				(crispin setLoop: 7 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(18
				(crispin setLoop: 9 cycleSpeed: 4)
				(crispin setCycle: MouthSync 5214)
				(SpeakAudio 5214 self)
			)
			(19
				(if
					(and
						(not (== howFast 0))
						(not (== (theMusic prevSignal?) -1))
						(< (theMusic prevSignal?) 10)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(20
				(crispin setCycle: BegLoop self)
			)
			(21
				(cls)
				(crispin setLoop: 8 setCycle: EndLoop)
				(SpeakAudio 5215 self)
			)
			(22
				(crispin setCycle: 0)
				(= cycles 1)
			)
			(23
				(cls)
				(egoHead init: setPri: 10 signal: 16400 posn: 93 110)
				(egoHead setCycle: MouthSync 5216)
				(SpeakAudio 5216 self)
			)
			(24
				(egoHead setCycle: 0 hide:)
				(crispin setCycle: BegLoop self)
			)
			(25
				(cls)
				(crispin setLoop: 9)
				(theMusic number: 147 loop: 1 play:)
				(crispin setCycle: MouthSync 5217)
				(SpeakAudio 5217 self)
			)
			(26
				(crispin setCycle: BegLoop self)
			)
			(27
				(ego setLoop: 8)
				(egoTemp
					init:
					view: 916
					setLoop: 8
					posn: 93 136
					setCycle: EndLoop
				)
				(cls)
				(egoHead posn: 93 110 show:)
				(= cycles 1)
			)
			(28
				(if
					(and
						(< (theMusic prevSignal?) 10)
						(not (== (theMusic prevSignal?) -1))
						(not (== howFast 0))
					)
					(-- state)
				)
				(= cycles 1)
			)
			(29
				(egoHead setCycle: MouthSync 5218)
				(SpeakAudio 5218 self)
			)
			(30
				(if
					(and
						(< (theMusic prevSignal?) 20)
						(not (== howFast 0))
						(not (== (theMusic prevSignal?) -1))
					)
					(-- state)
				)
				(= cycles 1)
			)
			(31
				(egoHead
					view: 933
					loop: 14
					hide:
					posn: 93 110
					setCycle: 0
				)
				(egoTemp setCycle: BegLoop self)
			)
			(32
				(egoTemp dispose:)
				(ego setLoop: 0 show:)
				(cls)
				(crispin setLoop: 9)
				(= cycles 1)
			)
			(33
				(alexander dispose:)
				(valanice dispose:)
				(rosella dispose:)
				(crispin setCycle: RandCycle)
				(SpeakAudio 5219 self)
			)
			(34
				(if
					(and
						(not (== howFast 0))
						(< (theMusic prevSignal?) 30)
						(not (== (theMusic prevSignal?) -1))
					)
					(-- state)
				)
				(= cycles 1)
			)
			(35
				(cls)
				(SpeakAudio 5220 self)
			)
			(36
				(if
					(and
						(< (theMusic prevSignal?) 40)
						(not (== howFast 0))
						(not (== (theMusic prevSignal?) -1))
					)
					(-- state)
				)
				(= cycles 1)
			)
			(37
				(cls)
				(SpeakAudio 5221 self)
			)
			(38
				(if
					(and
						(not (== howFast 0))
						(< (theMusic prevSignal?) 50)
						(not (== (theMusic prevSignal?) -1))
					)
					(-- state)
				)
				(= cycles 1)
			)
			(39
				(cls)
				(crispin setCycle: MouthSync 5222)
				(SpeakAudio 5222 self)
			)
			(40
				(if (not (== (theMusic prevSignal?) -1)) (-- state))
				(= cycles 1)
			)
			(41
				(crispin setCycle: BegLoop self)
			)
			(42
				(cls)
				(theMusic number: 148 loop: 1 play:)
				(crispin setLoop: 8 setCycle: EndLoop self)
			)
			(43
				(crispin setCycle: MouthSync 5223)
				(SpeakAudio 5223 self)
			)
			(44
				(cls)
				(theAudio number: 8140 loop: 1 play:)
				(crispin setCycle: BegLoop)
				(cedric cel: 0 setLoop: 8 setCycle: EndLoop self)
			)
			(45
				(cedric setLoop: 5 cycleSpeed: 2 setCycle: Forward)
				(= seconds 3)
			)
			(46
				(cedric setLoop: 6 setCycle: EndLoop self)
			)
			(47
				(if
					(and
						(< (theMusic prevSignal?) 10)
						(not (== (theMusic prevSignal?) -1))
						(not (== howFast 0))
					)
					(-- state)
				)
				(= cycles 1)
			)
			(48
				(cedricHead init:)
				(cedricHead setCycle: MouthSync 3004)
				(SpeakAudio 3004 self)
			)
			(49
				(if
					(and
						(not (== howFast 0))
						(not (== (theMusic prevSignal?) -1))
						(< (theMusic prevSignal?) 20)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(50
				(cls)
				(cedricHead setCycle: 0 hide:)
				(crispin setLoop: 9)
				(crispin setCycle: MouthSync 5225)
				(SpeakAudio 5225 self)
			)
			(51
				(cls)
				(cedricHead show:)
				(crispin setCycle: 0)
				(cedricHead setCycle: MouthSync 5226)
				(SpeakAudio 5226 self)
			)
			(52
				(cls)
				(cedricHead setCycle: 0 hide:)
				(crispin setCycle: MouthSync 5227)
				(SpeakAudio 5227 self)
			)
			(53
				(crispin setCycle: BegLoop self)
			)
			(54
				(cls)
				(crispin setLoop: 9)
				(crispin setCycle: MouthSync 5228)
				(SpeakAudio 5228 self)
			)
			(55
				(crispin setCycle: BegLoop self)
			)
			(56
				(cls)
				(cassimaHead
					view: 933
					loop: 10
					posn: 71 112
					setPri: 12
					init:
				)
				(cassimaHead setCycle: MouthSync 1088)
				(SpeakAudio 1088 self)
			)
			(57
				(cls)
				(cassimaHead setCycle: 0 dispose:)
				(alexHead setLoop: 13 setCycle: MouthSync 5230)
				(SpeakAudio 5230 self)
			)
			(58
				(alexHead setLoop: 12 setCycle: BegLoop)
				(crispin setLoop: 8 cel: 0 setCycle: EndLoop self)
			)
			(59
				(crispin setCycle: BegLoop)
				(theAudio number: 8140 loop: 1 play:)
				(cassima view: 933 setLoop: 7 setCycle: EndLoop self)
			)
			(60
				(cls)
				(egoHead
					show:
					setPri: 10
					posn: (egoHead x?) (egoHead y?)
				)
				(cassima dispose:)
				(egoHead setCycle: MouthSync 5231)
				(SpeakAudio 5231 self)
			)
			(61
				(cls)
				(egoHead setCycle: 0 hide:)
				(crispin setLoop: 9)
				(crispin setCycle: MouthSync 5232)
				(SpeakAudio 5232 self)
			)
			(62
				(crispin setCycle: BegLoop self)
			)
			(63
				(cls)
				(cedricHead show:)
				(cedricHead setCycle: MouthSync 5233)
				(SpeakAudio 5233 self)
			)
			(64
				(cls)
				(cedricHead setCycle: 0 hide:)
				(crispin
					setLoop: 9
					setPri: (+ (ego priority?) 1)
					cycleSpeed: 4
				)
				(crispin setCycle: MouthSync 5234)
				(SpeakAudio 5234 self)
			)
			(65
				(cls)
				(crispin setCycle: MouthSync 5235)
				(SpeakAudio 5235 self)
			)
			(66
				(crispin setLoop: 8 cel: 0 setCycle: EndLoop self)
			)
			(67 (= seconds 2))
			(68
				(theAudio number: 8140 loop: 1 play:)
				(alexHead dispose:)
				(ego cycleSpeed: 2 setLoop: 0 setCycle: EndLoop self)
			)
			(69
				(ego setLoop: 1)
				(ego cel: (- (NumCels ego) 1) setCycle: BegLoop self)
			)
			(70 (ego hide:) (= cycles 3))
			(71 (curRoom newRoom: 123))
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
		signal $4000
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
		signal $0010
	)
)

(instance cedric of Actor
	(properties
		x 232
		y 152
		view 720
		loop 2
		cel 2
		signal $4000
		illegalBits $0000
	)
)

(instance cassimaHead of Prop
	(properties
		x 53
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
		(self signal: 16384)
	)
)

(instance egoTemp of Prop
	(properties)
)

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
		(self signal: 16400 setPri: 10)
	)
)

(instance coals of Prop
	(properties
		x 281
		y 152
		view 933
		loop 15
		priority 12
		signal $4010
		cycleSpeed 10
		detailLevel 3
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

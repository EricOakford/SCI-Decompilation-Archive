;;; Sierra Script 1.0 - (do not remove this comment)
(script# 127)
(include game.sh) (include "127.shm")
(use Main)
(use Talker)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm127 0
	danteTalker 3
	beaTalker 10
	quirkTalker 14
)

(local
	local0
	local1
)
(procedure (localproc_006e)
	(bobHead setCel: 0)
	(danteHead setCel: 1)
	(kaarenHead setCel: 0)
	(quirkHead setCel: 0)
	(randyHead setCel: 0)
)

(procedure (localproc_00a1)
	(bobHead setCel: 2)
	(danteHead setCel: 0)
	(kaarenHead setCel: 3)
	(randyHead setCel: 3)
)

(procedure (localproc_00cc)
	(quirkHead setLoop: 2 setCel: 1)
	(bobHead setCel: 1)
	(danteHead setCel: 1)
	(kaarenHead setCel: 1)
	(quirkHead setCel: 1)
	(randyHead setCel: 1)
)

(instance rm127 of Room
	(properties
		picture 34
		style FADEOUT
	)
	
	(method (init)
		(self setRegions: 109)
		(LoadMany RES_VIEW 150 151 153 152)
		(LoadMany RES_MESSAGE 127)
		(confDoor init: setPri: 9)
		(beaBody init:)
		(beaHead init:)
		(kaarenHead init:)
		(oldMan init:)
		(quirkHead init:)
		(danteHead init:)
		(randyHead init:)
		(super init:)
		(theMusic1 number: 15 loop: -1 play:)
		(self setScript: sArgument)
	)
	
	(method (dispose)
		(theMusic1 stop:)
		(super dispose:)
	)
)

(instance sArgument of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(theMusic2 number: 145 setLoop: -1 play:)
				(Palette PALIntensity 1 254 50)
				(= cycles 2)
			)
			(2
				(Palette PALIntensity 1 254 100)
				(= cycles 2)
			)
			(3
				(Palette PALIntensity 1 254 50)
				(= cycles 2)
			)
			(4
				(Palette PALIntensity 1 254 100)
				(= cycles 5)
			)
			(5
				(Palette PALIntensity 1 254 50)
				(= cycles 2)
			)
			(6
				(Palette PALIntensity 1 254 100)
				(= cycles 2)
			)
			(7
				(Palette PALIntensity 1 254 50)
				(= cycles 2)
			)
			(8
				(Palette PALIntensity 1 254 100)
				(= cycles 2)
			)
			(9
				(Palette PALIntensity 1 254 50)
				(= cycles 2)
			)
			(10
				(Palette PALIntensity 1 254 100)
				(theMusic2 stop:)
				(= cycles 2)
			)
			(11
				(= local0 0)
				(messager say: N_ARGUMENT NULL NULL 1 self)
			)
			(12
				(localproc_00cc)
				(= cycles 2)
			)
			(13
				(messager say: N_ARGUMENT NULL NULL 2 self)
			)
			(14
				(messager say: N_ARGUMENT NULL NULL 3 self)
			)
			(15
				(beaBody setCycle: EndLoop self)
				(rog
					loop: 4
					cel: 0
					init:
					posn: 115 119
					setCycle: EndLoop self
				)
			)
			(16 0)
			(17
				(localproc_006e)
				(= cycles 2)
			)
			(18
				(messager say: N_ARGUMENT NULL NULL 4 self)
			)
			(19
				(messager say: N_ARGUMENT NULL NULL 5 self)
			)
			(20
				(beaBody setCycle: BegLoop self)
				(rog loop: 5 cel: 0 posn: 249 120 setCycle: EndLoop self)
			)
			(21 0)
			(22
				(messager say: N_ARGUMENT NULL NULL 6 self)
			)
			(23
				(localproc_00cc)
				(= cycles 2)
			)
			(24
				(messager say: N_ARGUMENT NULL NULL 7 self)
			)
			(25
				(rog loop: 0 cel: 0 posn: 249 119 setCycle: EndLoop self)
			)
			(26
				(localproc_00a1)
				(= cycles 2)
			)
			(27
				(= local0 2)
				(messager say: N_ARGUMENT NULL NULL 8 self)
			)
			(28
				(beaHead hide:)
				(beaBody loop: 1 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(29
				(beaBody setCel: 5)
				(beaHead setCel: 0 posn: 176 97 show:)
				(localproc_006e)
				(= cycles 2)
			)
			(30
				(messager say: N_ARGUMENT NULL NULL 9 self)
			)
			(31
				(rog loop: 1 cel: 0 setCycle: EndLoop self)
				(theMusic2 number: 152 setLoop: 1 play:)
			)
			(32
				(localproc_00a1)
				(= cycles 2)
			)
			(33
				(= local0 2)
				(messager say: N_ARGUMENT NULL NULL 10 self)
			)
			(34
				(messager say: N_ARGUMENT NULL NULL 11 self)
			)
			(35
				(localproc_006e)
				(= cycles 2)
			)
			(36
				(messager say: N_ARGUMENT NULL NULL 12 self)
			)
			(37
				(localproc_00cc)
				(= cycles 2)
			)
			(38
				(messager say: N_ARGUMENT NULL NULL 13 self)
			)
			(39
				(localproc_006e)
				(= cycles 2)
			)
			(40
				(messager say: N_ARGUMENT NULL NULL 14 self)
			)
			(41
				(localproc_00cc)
				(= cycles 2)
			)
			(42
				(messager say: N_ARGUMENT NULL NULL 15 self)
			)
			(43
				(localproc_00a1)
				(= cycles 2)
			)
			(44
				(= local0 1)
				(messager say: N_ARGUMENT NULL NULL 16 self)
			)
			(45
				(localproc_006e)
				(= cycles 2)
			)
			(46
				(messager say: N_ARGUMENT NULL NULL 17 self)
			)
			(47
				(localproc_00cc)
				(= cycles 2)
			)
			(48
				(messager say: N_ARGUMENT NULL NULL 18 self)
			)
			(49
				(localproc_006e)
				(= cycles 2)
			)
			(50
				(messager say: N_ARGUMENT NULL NULL 19 self)
			)
			(51
				(beaHead dispose:)
				(beaBody setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(52
				(beaBody setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(53
				(confDoor setCycle: EndLoop self)
				(theMusic2 number: 103 setLoop: 1 play:)
			)
			(54
				(theMusic2 number: 102 setLoop: 1 play:)
				(beaBody setLoop: 5 setCel: 0 setCycle: EndLoop)
				(rog
					loop: 2
					cel: 0
					setPri: 10
					posn: 180 146
					setCycle: EndLoop self
				)
			)
			(55
				(theMusic2 number: 136 setLoop: 1 play: self)
			)
			(56
				(= local1 1)
				(localproc_006e)
				(= cycles 2)
			)
			(57
				(messager say: N_ARGUMENT NULL NULL 20 self)
			)
			(58
				(beaBody setLoop: 6 setCel: 0 setCycle: EndLoop self)
			)
			(59
				(beaBody setCycle: BegLoop self)
			)
			(60
				(beaBody
					setLoop: 7
					setCel: 0
					setPri: 5
					signal: 16
					setCycle: EndLoop self
				)
			)
			(61 (= seconds 2))
			(62
				(oldMan setCel: 1 posn: 48 116)
				(rog setLoop: 3 cel: 0 setCycle: EndLoop self)
				(beaBody
					setLoop: 8
					setCel: 0
					posn: 100 102
					setCycle: EndLoop self
				)
			)
			(63 0)
			(64
				(confDoor setCycle: BegLoop self)
				(theMusic2 number: 103 setLoop: 1 play:)
			)
			(65
				(messager say: N_ARGUMENT NULL NULL 21 self)
			)
			(66
				(Bset fSawArgument)
				(curRoom newRoom: 125)
				(self dispose:)
			)
		)
	)
)

(instance beaBody of Actor
	(properties
		x 182
		y 139
		view 150
		priority 11
		signal fixPriOn
	)
)

(instance beaHead of Actor
	(properties
		x 178
		y 95
		view 150
		loop 10
		cel 1
		priority 12
		signal fixPriOn
	)
)

(instance rog of Actor
	(properties
		view 151
		priority 15
		cycleSpeed 8
	)
)

(instance confDoor of Prop
	(properties
		x 181
		y 134
		view 152
		priority 11
	)
)

(instance beaMouth of Prop
	(properties
		nsTop 8
		nsLeft 3
		view 150
		loop 2
		signal ignrAct
	)
)

(instance danteMouth of Prop
	(properties
		nsTop 25
		nsLeft 5
		view 153
		loop 6
		signal ignrAct
	)
)

(instance quirkMouth of Prop
	(properties
		view 153
		signal ignrAct
	)
)

(instance beaTalker of Talker
	(properties
		view 150
		loop 10
		talkWidth 200
		textX -120
		textY -80
	)
	
	(method (init)
		(if (== (beaBody loop?) 0)
			(self cel: 1 x: 174 y: 87)
		else
			(self cel: 0 x: 172 y: 89)
		)
		(if (== local1 1)
			(self loop: 11 cel: 0 x: 180 y: 110)
			(beaMouth loop: 11 cel: 1)
		)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 171
			tailY: 75
			xOffset: 0
		)
		(super init: 0 0 beaMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance danteTalker of Talker
	(properties
		x 260
		y 105
		view 153
		loop 5
		cel 1
		talkWidth 200
		textX -240
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 244
			tailY: 82
			xOffset: -40
		)
		(super init: 0 0 danteMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance quirkTalker of Talker
	(properties
		view 153
		loop 2
		talkWidth 170
	)
	
	(method (init)
		(switch local0
			(0
				(self cel: 3 x: 79 y: 101)
				(quirkMouth loop: 13 nsLeft: 0 nsTop: 12)
				(quirkHead setCel: 3)
			)
			(1
				(self cel: 1 x: 80 y: 100)
				(quirkMouth loop: 3 nsLeft: 2 nsTop: 13)
				(quirkHead setCel: 1)
			)
			(else 
				(self cel: 0 x: 81 y: 101)
				(quirkMouth loop: 8 nsLeft: 7 nsTop: 8)
				(quirkHead setCel: 0)
			)
		)
		(= font userFont)
		((= systemWindow theSpeakWindow)
			tailX: 100
			tailY: 82
			xOffset: 13
		)
		(super init: 0 0 quirkMouth &rest)
	)
	
	(method (dispose)
		(if (== local0 0)
			(randyHead setCel: 3 posn: 218 114)
			(bobHead init:)
		)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance kaarenHead of View
	(properties
		x 20
		y 110
		view 153
		cel 4
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance oldMan of View
	(properties
		x 45
		y 112
		view 153
		loop 1
		cel 2
		priority 12
		signal (| ignrAct fixPriOn)
	)
)

(instance quirkHead of View
	(properties
		x 88
		y 115
		view 153
		loop 2
		cel 3
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance danteHead of View
	(properties
		x 260
		y 106
		view 153
		loop 5
		cel 2
		priority 8
		signal (| ignrAct fixPriOn)
	)
)

(instance randyHead of View
	(properties
		x 223
		y 104
		view 153
		loop 4
		cel 4
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance bobHead of View
	(properties
		x 222
		y 105
		view 153
		loop 7
		cel 2
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

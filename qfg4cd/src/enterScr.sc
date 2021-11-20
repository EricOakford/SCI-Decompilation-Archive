;;; Sierra Script 1.0 - (do not remove this comment)
(script# 731)
(include sci.sh)
(use Main)
(use Motion)
(use System)

(public
	enterScr 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9 =  100
)
(instance enterScr of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and local0 (not (-- local1)))
			(Palette palSET_FLAG 0 255 local9)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local1 30)
				(= seconds 1)
			)
			(1
				(ego
					drop: 55
					setCycle: Fwd
					signal: (| (ego signal?) $4000)
					setMotion: MoveTo 170 120 self
				)
			)
			(2
				(ego
					setLoop: 5 1
					posn: 170 79
					setCel: 0
					setPri: 119
					setCycle: End self
				)
			)
			(3
				(ego view: 5 setLoop: 2 1 setCel: 0 posn: 170 80)
				(= seconds 2)
			)
			(4 (messager say: 4 6 1 0 self))
			(5 (= seconds 1))
			(6 (messager say: 5 6 1 0 self))
			(7 (= seconds 1))
			(8 (messager say: 6 6 2 0 self))
			(9
				(= local0 1)
				(messager say: 4 6 3 0 self)
			)
			(10
				(= local2 ((ScriptID 730 4) new:))
				(= local3 ((ScriptID 730 4) new:))
				(= local4 ((ScriptID 730 4) new:))
				(= local5 ((ScriptID 730 4) new:))
				(= local6 ((ScriptID 730 4) new:))
				(= local7 ((ScriptID 730 4) new:))
				(local2
					setLoop: 0 1
					setCel: 1
					posn: 263 122
					init:
					cycleSpeed: 18
					setCycle: Fwd
				)
				(local3
					setLoop: 1 1
					setCel: 5
					posn: 100 115
					init:
					cycleSpeed: 18
					setCycle: Fwd
				)
				(local4
					setLoop: 2 1
					setCel: 0
					posn: 124 119
					init:
					cycleSpeed: 18
					setCycle: Fwd
				)
				(local5
					setLoop: 3 1
					setCel: 1
					posn: 33 42
					init:
					cycleSpeed: 18
					setCycle: Fwd
				)
				(local6
					setLoop: 4 1
					setCel: 5
					posn: 188 56
					init:
					cycleSpeed: 18
					setCycle: Fwd
				)
				(local7
					setLoop: 5 1
					setCel: 4
					posn: 296 21
					init:
					cycleSpeed: 18
					setCycle: Fwd
				)
				(theMusic number: 104 loop: 1 play:)
				(= seconds 1)
			)
			(11
				((ScriptID 730 2)
					view: 677
					setLoop: 0 1
					setCel: 0
					signal: (| ((ScriptID 730 2) signal?) $0001)
					setCycle: CT 5 1 self
				)
			)
			(12
				((= local8 ((ScriptID 730 5) new:))
					x: 91
					y: 101
					setLoop: 3 1
					moveSpeed: 0
					setCycle: Fwd
					init:
					setMotion: MoveTo 176 87 self
				)
				((ScriptID 730 2) setCycle: End)
			)
			(13
				(local8 setLoop: 5 1 cel: 0 setCycle: End self)
			)
			(14
				(local8 dispose:)
				((ScriptID 730 6) init: setCycle: End)
				(ego view: 7 posn: 170 79 setLoop: 5 1 setCel: 0)
				(= seconds 4)
			)
			(15
				(messager say: 4 6 4 0 self)
			)
			(16
				((ScriptID 730 3)
					setLoop: 0 1
					setCel: 0
					setPri: (+ ((ScriptID 730 8) priority?) 17)
					setCycle: End self
				)
			)
			(17
				((ScriptID 730 3) setCel: 0)
				((= local8 ((ScriptID 730 5) new:))
					x: 190
					y: 65
					setLoop: 1 1
					setCycle: Fwd
					moveSpeed: 0
					init:
					setMotion: MoveTo 76 100 self
				)
			)
			(18
				((ScriptID 730 7) init: setCycle: End)
				(local8 setLoop: 2 1 cel: 0 setCycle: End self)
			)
			(19
				(local8 dispose:)
				((ScriptID 730 2)
					view: 737
					setLoop: 0 1
					setCel: 0
					setCycle: End self
				)
			)
			(20
				(messager say: 5 6 3 0 self)
			)
			(21
				((ScriptID 730 2)
					view: 677
					setLoop: 0 1
					setCel: 0
					setCycle: CT 5 1 self
				)
			)
			(22
				((= local8 ((ScriptID 730 5) new:))
					x: 91
					y: 101
					setLoop: 3 1
					moveSpeed: 0
					init:
					setMotion: MoveTo 237 70 self
				)
				((ScriptID 730 2) setCycle: End)
			)
			(23
				(local8 setLoop: 5 1 cel: 0 setCycle: End self)
				((ScriptID 730 8) init: setCycle: End)
				((ScriptID 730 3) setLoop: 1 1 setCel: 0 setCycle: End)
			)
			(24
				(local8 dispose:)
				(= ticks 30)
			)
			(25
				((ScriptID 730 3) setLoop: 0 1 setCel: 0)
				(= seconds 2)
			)
			(26
				((ScriptID 730 3) setCycle: End self)
			)
			(27
				((= local8 ((ScriptID 730 5) new:))
					x: 190
					y: 65
					setLoop: 1 1
					moveSpeed: 0
					init:
					setMotion: MoveTo 76 100 self
				)
			)
			(28
				((ScriptID 730 3) setLoop: 0 1 setCel: 0)
				(local8 setLoop: 2 1 cel: 0 setCycle: End self)
			)
			(29
				(local8 dispose:)
				((ScriptID 730 2)
					view: 737
					setLoop: 0 1
					setCel: 0
					setCycle: End self
				)
			)
			(30
				((ScriptID 730 2)
					view: 677
					setLoop: 0 1
					setCel: 0
					setCycle: CT 5 1 self
				)
			)
			(31
				((= local8 ((ScriptID 730 5) new:))
					x: 91
					y: 101
					setLoop: 3 1
					moveSpeed: 0
					setCycle: Fwd
					init:
					setMotion: MoveTo 237 70 self
				)
				((ScriptID 730 2) setCycle: End)
			)
			(32
				(local8 setLoop: 5 1 cel: 0 setCycle: End self)
			)
			(33
				(local8 dispose:)
				((ScriptID 730 3)
					setLoop: 1 1
					setCel: 0
					setCycle: End self
				)
			)
			(34
				((ScriptID 730 3) setLoop: 0 1 setCel: 0)
				(messager say: 4 6 5 0 self)
			)
			(35
				((ScriptID 730 3) setCel: 3 setCycle: End self)
			)
			(36
				((= local8 ((ScriptID 730 5) new:))
					x: 190
					y: 65
					setLoop: 1 1
					setCycle: Fwd
					init:
					setMotion: MoveTo 76 100 self
				)
			)
			(37
				((ScriptID 730 3) setLoop: 0 1 setCel: 0)
				(local8 setLoop: 2 1 cel: 0 setCycle: End self)
			)
			(38
				(local8 dispose:)
				((ScriptID 730 2)
					view: 737
					setLoop: 0 1
					setCel: 0
					setCycle: End self
				)
				(theMusic number: 109 loop: -1 play:)
			)
			(39
				(messager say: 5 6 6 0 self)
			)
			(40
				(messager say: 4 6 6 0 self)
			)
			(41
				((ScriptID 730 2)
					view: 738
					setLoop: 0 1
					setCel: 0
					posn: 76 136
					setCycle: End
				)
				((ScriptID 730 3)
					view: 739
					setLoop: 0 1
					setCel: 0
					posn: 235 100
					setCycle: End self
				)
			)
			(42
				((ScriptID 730 3)
					setLoop: 1 1
					setCel: 0
					posn: 134 104
					ignoreActors: 1
					setCycle: End self
				)
			)
			(43
				((ScriptID 730 2)
					setLoop: 1 1
					setCel: 0
					setCycle: CT 1 1 self
				)
			)
			(44
				((ScriptID 730 3) hide: dispose:)
				((ScriptID 730 2)
					setLoop: 1 1
					setCel: 1
					setCycle: End self
				)
			)
			(45
				((ScriptID 730 2)
					setLoop: 2 1
					setCel: 0
					setCycle: End self
				)
			)
			(46
				(messager say: 5 6 7 0 self)
			)
			(47
				((ScriptID 730 2)
					view: 677
					setLoop: 0 1
					setCel: 0
					posn: 73 141
					setCycle: CT 5 1 self
				)
			)
			(48
				((= local8 ((ScriptID 730 5) new:))
					view: 747
					setLoop: 1 1
					x: 91
					y: 101
					moveSpeed: 0
					init:
					setCycle: Fwd
					setMotion: MoveTo 176 87 self
				)
				((ScriptID 730 2) setCycle: End)
			)
			(49
				(local8 setLoop: 0 1 setCel: 0 setCycle: End self)
			)
			(50
				(local8 dispose:)
				(messager say: 5 6 8 0 self)
			)
			(51
				(ego setCel: 0 setCycle: End self)
			)
			(52
				(ego view: 5 setLoop: 2 1 setCel: 0 posn: 170 80)
				(= seconds 2)
			)
			(53
				(theMusic number: 105 loop: -1 play:)
				(switch arcadeLevel
					(1 (PalVary pvINIT 0 600))
					(2 (PalVary pvINIT 0 500))
					(3 (PalVary pvINIT 0 400))
				)
				((ScriptID 730 9) setScript: (ScriptID 730 10))
				(theGame handsOn:)
				(theIconBar disable: 0)
				(self dispose:)
			)
		)
	)
)

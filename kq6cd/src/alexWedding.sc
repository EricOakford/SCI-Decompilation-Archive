;;; Sierra Script 1.0 - (do not remove this comment)
(script# 743)
(include sci.sh)
(use Main)
(use rm740)
(use Kq6Procs)
(use Motion)
(use Actor)
(use System)

(public
	alexWedding 0
)

(local
	local0
	local1
)
(instance alexWedding of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar disable: 6)
				(= register (>> (MemoryInfo 4) $0006))
				(= local0 (== ((inventory at: 25) owner?) curRoomNum))
				(= ticks 30)
			)
			(1
				(messager say: 1 0 16 1 self 743)
			)
			(2 (= ticks 60))
			(3
				(messager say: 1 0 16 2 self 743)
			)
			(4
				(cast eachElementDo: #dispose)
				(theIconBar
					enable:
					disable: 0 1 2 3 4 5 6
					height: -100
					activateHeight: -100
				)
				(Cursor showCursor: 0)
				(curRoom drawPic: 165 10)
				(= cycles 3)
			)
			(5
				(messager say: 1 0 7 0 self 165)
			)
			(6
				(cond 
					((OneOf ((inventory at: 39) owner?) 140 210) (= state 10) (messager say: 1 0 3 0 self 165))
					((ego has: 39) (= state 10) (messager say: 1 0 4 0 self 165))
					(else (messager say: 1 0 2 1 self 165))
				)
			)
			(7
				(extraProp
					view: 165
					init:
					setLoop: 4
					cel: 0
					cycleSpeed: 4
					posn: 167 62
					setCycle: End self
				)
			)
			(8 (= cycles 5))
			(9
				((ScriptID 740 7)
					add: 165 1 0 2 2
					add: 165 1 0 2 3
					init: self
				)
			)
			(10
				(= state 12)
				(messager say: 1 0 2 4 self 165)
			)
			(11
				(curRoom drawPic: 166 10)
				(extraProp
					view: 166
					init:
					setLoop: 0
					cel: 0
					cycleSpeed: 12
					posn: 82 127
				)
				(= seconds 3)
			)
			(12
				(extraProp setCycle: End self)
			)
			(13
				(extraProp dispose:)
				(proc740_10)
				(= cycles 3)
			)
			(14
				(theIconBar height: 0 activateHeight: 0)
				(Cursor showCursor: 1)
				(theIconBar enable:)
				(= cycles 2)
			)
			(15
				(UnLoad 128 7411)
				(if (not (Btst 15))
					(= state 18)
					(= ticks 30)
				else
					(messager say: 1 0 13 1 self 743)
				)
			)
			(16
				((ScriptID 740 11)
					setLoop: 0
					cel: 0
					posn: 135 144
					cycleSpeed: 8
					setCycle: End
				)
				((ScriptID 740 1)
					setLoop: 2
					cel: 0
					cycleSpeed: 8
					setCycle: End
				)
				(= seconds 3)
			)
			(17
				(messager say: 1 0 13 2 self 743)
			)
			(18
				(= state 24)
				((ScriptID 740 11) cel: 0)
				(= ticks 60)
			)
			(19
				((ScriptID 740 11)
					setLoop: 2
					cel: 0
					posn: 128 147
					stopUpd:
				)
				(= cycles 2)
			)
			(20
				(messager say: 1 0 14 1 self 743)
			)
			(21
				((ScriptID 740 4)
					view: 7465
					posn: (+ global164 32) (- global165 4)
					setCel: 255
					setLoop: 0
					scaleSignal: 1
					scaleX: 117
					scaleY: 117
					setCycle: Beg self
				)
			)
			(22
				((ScriptID 740 7)
					add: 743 1 0 14 2
					add: 743 1 0 14 3
					init: self
				)
			)
			(23
				((ScriptID 740 4) setCycle: End self)
			)
			(24
				((ScriptID 740 4)
					view: 746
					setLoop: 0
					cel: 0
					posn: global164 global165
					scaleSignal: 1
					scaleX: 115
					scaleY: 115
					stopUpd:
				)
				(= ticks 30)
			)
			(25
				(if local0
					(messager say: 1 0 12 1 self 743)
				else
					(= state 28)
					((ScriptID 740 1) setLoop: 0 cel: 0 cycleSpeed: 8)
					(= ticks 30)
				)
			)
			(26
				((ScriptID 740 12) setLoop: 2 cel: 0 setCycle: End self)
			)
			(27
				(messager say: 1 0 12 2 self 743)
			)
			(28
				(++ state)
				((ScriptID 740 12) setCycle: Beg self)
			)
			(29
				((ScriptID 740 7)
					add: 743 1 0 11 1
					add: 743 1 0 11 2
					add: 743 1 0 11 3
					init: self
				)
			)
			(30
				(messager say: 1 0 15 1 self 743)
			)
			(31
				((ScriptID 740 1)
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
				(if local0
					((ScriptID 740 12)
						setLoop: 3
						cel: 0
						setCycle: End (ScriptID 740 12)
					)
				)
				(if (Btst 15)
					((ScriptID 740 11) setLoop: 2 cel: 0 setCycle: Fwd)
				)
				(theMusic
					number: (if global169 1744 else 744)
					setLoop: -1
					play:
				)
			)
			(32
				(messager say: 1 0 15 2 self 743)
			)
			(33
				(cond 
					((not (Btst 10))
						((ScriptID 740 4)
							view: 746
							setLoop: 0
							cel: 0
							setCycle: End self
						)
					)
					((and (not (Btst 15)) (not local0))
						((ScriptID 740 4)
							view: 7461
							setLoop: 0
							cel: 0
							setCycle: End self
						)
					)
					(else
						((ScriptID 740 4)
							view: 7463
							setLoop: 0
							cel: 0
							scaleSignal: 1
							scaleX: 115
							scaleY: 115
							setCycle: End self
						)
					)
				)
				((ScriptID 740 21) setCycle: End (ScriptID 740 21))
				((ScriptID 740 22) setCycle: End (ScriptID 740 22))
				((ScriptID 740 11) cel: 0 stopUpd:)
			)
			(34
				(if (Btst 10)
					((ScriptID 740 4)
						view: 746
						setLoop: 0
						cel: 0
						setCycle: 0
						scaleSignal: 1
						scaleX: 115
						scaleY: 115
					)
				)
				(= seconds 3)
			)
			(35
				((ScriptID 740 1) setCycle: Beg self)
			)
			(36
				(if (not (Btst 15))
					((ScriptID 740 1) setLoop: 0 cel: 0)
				else
					((ScriptID 740 1) setLoop: 2 setCycle: End)
				)
				((ScriptID 740 21) cue: 99)
				((ScriptID 740 22) cue: 99)
				(= seconds 3)
			)
			(37
				(if (Btst 15)
					((ScriptID 740 7)
						add: 743 1 0 6 1
						add: 743 1 0 6 2
						add: 743 1 0 6 3
						add: 743 1 0 6 4
						add: 743 1 0 6 5
						add: 743 1 0 6 6
					)
				else
					((ScriptID 740 7)
						add: 743 1 0 7 1
						add: 743 1 0 7 2
						add: 743 1 0 7 3
						add: 743 1 0 7 4
						add: 743 1 0 7 5
						add: 743 1 0 7 6
					)
				)
				((ScriptID 740 7) init: self)
			)
			(38
				((ScriptID 740 7)
					add: 743 1 0 8 1
					add: 743 1 0 8 3
					init: self
				)
			)
			(39
				(if local0
					(self setScript: hugStuff self)
				else
					((ScriptID 740 7)
						add: 743 1 0 10 1
						add: 743 1 0 10 2
						add: 743 1 0 10 3
						add: 743 1 0 10 4
						init: self
					)
				)
			)
			(40
				(if (Btst 15)
					((ScriptID 740 7) add: 743 1 0 2 1 add: 743 1 0 2 2)
				else
					((ScriptID 740 7) add: 743 1 0 1 1 add: 743 1 0 1 2)
				)
				((ScriptID 740 7) init: self)
			)
			(41
				(if local0
					(SetSynonyms 2 {CASSIMA})
					((ScriptID 740 7)
						add: 743 1 0 19 1
						add: 743 1 0 19 2
						add: 743 1 0 19 3
						init: self
					)
					(SetSynonyms 2 {VALANICE})
				else
					(= cycles 2)
				)
			)
			(42
				(if (== ((ScriptID 740 1) loop?) 2)
					((ScriptID 740 1) setCycle: Beg self)
				else
					(= cycles 2)
				)
			)
			(43
				((ScriptID 740 1) setLoop: 0 cel: 0)
				(= ticks 30)
			)
			(44
				(if local0
					(messager say: 1 0 5 1 self 743)
				else
					(messager say: 1 0 4 1 self 743)
				)
			)
			(45
				(if ((ScriptID 80 0) tstFlag: 710 16)
					(SetSynonyms 2 {JOLLO})
					(SetSynonyms 2 {VALANICE})
					(SetSynonyms 0 {CASSIMA})
					(SetSynonyms 0 {SALADIN})
					((ScriptID 740 7)
						add: 743 1 0 17 1
						add: 743 1 0 17 2
						init: self
					)
				else
					((ScriptID 740 7)
						add: 743 1 0 18 1
						add: 743 1 0 18 2
						init: self
					)
				)
			)
			(46
				(messager say: 1 0 3 1 self 743)
			)
			(47
				((ScriptID 740 1) setLoop: 2 cel: 0 setCycle: End self)
			)
			(48
				(narrator keepWindow: 1 modeless: 0)
				(messager say: 1 0 3 2 self 743)
			)
			(49
				(DisposeScript 1001)
				(DisposeScript 1005)
				(DisposeScript 1026)
				(DisposeScript 1004)
				(if (and local0 ((ScriptID 80 0) tstFlag: 710 16))
					((ScriptID 740 16) setCycle: Fwd)
					((ScriptID 740 17) setCycle: Fwd)
					((ScriptID 740 19) view: 7472 setLoop: 1 setCycle: Fwd)
					((ScriptID 740 18) view: 7472 setLoop: 0 setCycle: Fwd)
					((ScriptID 740 20) view: 7472 setLoop: 2 setCycle: Fwd)
					(clap1 init: setCycle: Fwd)
					(clap2 init: setCycle: Fwd)
					((ScriptID 740 14)
						view: 7471
						setLoop: 2
						cel: 0
						posn: 236 189
						setPri: 15
						setCycle: End (ScriptID 740 14)
					)
					((ScriptID 740 15)
						view: 7471
						setLoop: 3
						cel: 0
						posn: 248 186
						setPri: 14
						setCycle: End (ScriptID 740 15)
					)
				)
				(cond 
					((not (Btst 10))
						((ScriptID 740 4)
							view: 746
							setLoop: 0
							cel: 0
							setCycle: End
						)
					)
					((not (Btst 15)) ((ScriptID 740 4) setScript: clap 0 0))
					((not (cast contains: (ScriptID 740 3))) ((ScriptID 740 4) setScript: clap 0 1))
					(else
						((ScriptID 740 4)
							view: 7460
							setLoop: 0
							scaleSignal: 1
							scaleX: 115
							scaleY: 115
							setCycle: Fwd
						)
					)
				)
				(if (Btst 15)
					((ScriptID 740 11)
						setLoop: 2
						setCycle: End (ScriptID 740 11)
					)
				)
				((ScriptID 740 21) setCycle: End (ScriptID 740 21))
				((ScriptID 740 22) setCycle: End (ScriptID 740 22))
				(if (== ((inventory at: 25) owner?) 740)
					(self setScript: (ScriptID 752 2) self (ScriptID 740 3))
				else
					(= state 51)
					(= cycles 2)
				)
			)
			(50
				((ScriptID 740 3) posn: 195 152)
				(self setScript: (ScriptID 752 1) self (ScriptID 740 3))
			)
			(51
				((ScriptID 740 3) view: 7481 setLoop: 0 cel: 0)
				(= ticks 30)
			)
			(52
				(narrator keepWindow: 1 modeless: 0)
				((ScriptID 740 7)
					add: 743 1 0 3 3
					add: 743 1 0 3 4
					add: 743 1 0 3 5
					init: self
				)
			)
			(53
				(if (== ((inventory at: 25) owner?) 740)
					(= cycles 2)
				else
					((ScriptID 740 1) setLoop: 1 cel: 0 setCycle: End)
				)
				(theMusic number: 747 setLoop: 1 play: curRoom)
			)
			(54
				((ScriptID 740 3) cycleSpeed: 10 setCycle: End self)
			)
			(55
				((ScriptID 740 1)
					view: 7481
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
				((ScriptID 740 3) cel: 0)
			)
			(56
				((ScriptID 740 3) setCycle: End self)
			)
			(57
				((ScriptID 740 1) setLoop: 2 cel: 0 setCycle: End)
				((ScriptID 740 3) view: 7020 setLoop: 0 cel: 0 stopUpd:)
			)
		)
	)
)

(instance hugStuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= ((ScriptID 740 1) loop?) 2)
					((ScriptID 740 1) setLoop: 2 cel: 0 setCycle: End self)
				else
					(= cycles 2)
				)
			)
			(1 (= cycles 2))
			(2
				(messager say: 1 0 9 1 self 743)
			)
			(3
				((ScriptID 740 12)
					view: 7451
					setLoop: 0
					cel: 0
					setCycle: End self
				)
			)
			(4
				(messager say: 1 0 9 2 self 743)
			)
			(5
				((ScriptID 740 1) hide:)
				((ScriptID 740 12)
					setLoop: 1
					cel: 0
					posn: 221 145
					setCycle: End self
				)
			)
			(6
				(messager say: 1 0 9 3 self 743)
			)
			(7
				((ScriptID 740 12) setLoop: 2 cel: 0 setCycle: End self)
			)
			(8
				(SetSynonyms 2 {CASSIMA})
				(messager say: 1 0 9 4 self 743)
				(SetSynonyms 2 {VALANICE})
			)
			(9
				((ScriptID 740 1) show:)
				((ScriptID 740 12)
					setLoop: 0
					setCel: 255
					posn: 216 144
					cycleSpeed: 10
					setCycle: Beg self
				)
			)
			(10
				(messager say: 1 0 9 5 self 743)
			)
			(11
				((ScriptID 740 12) addToPic:)
				(DisposeScript 1065)
				(DisposeScript 1066)
				(= cycles 2)
			)
			(12
				((ScriptID 740 1) setPri: 8)
				((ScriptID 740 13)
					view: 7452
					setPri: 9
					setLoop: 0
					cel: 0
					posn: 228 145
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(13
				((ScriptID 740 1) hide:)
				((ScriptID 740 13)
					setLoop: 1
					cel: 0
					posn: 178 139
					setCycle: End self
				)
			)
			(14
				(messager say: 1 0 9 6 self 743)
			)
			(15
				(messager say: 1 0 9 7 self 743)
			)
			(16
				((ScriptID 740 1) show: setPri: -1)
				((ScriptID 740 13)
					setLoop: 2
					cel: 0
					posn: 227 146
					setCycle: End self
				)
			)
			(17
				((ScriptID 740 13)
					view: 745
					setLoop: 4
					cel: 2
					posn: 235 145
					signal: 26624
				)
				(= ticks 30)
			)
			(18
				((ScriptID 740 13) addToPic:)
				(DisposeScript 1067)
				(= cycles 2)
			)
			(19 (self dispose:))
		)
	)
)

(instance extraProp of Prop
	(properties
		view 165
		loop 4
		signal $6800
		detailLevel 2
	)
)

(instance clap1 of Prop
	(properties
		x 30
		y 145
		view 7471
		loop 5
		priority 15
		signal $4810
		cycleSpeed 4
		detailLevel 2
	)
)

(instance clap2 of Prop
	(properties
		x 54
		y 141
		view 7471
		loop 6
		priority 15
		signal $4810
		cycleSpeed 4
		detailLevel 2
	)
)

(instance clap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 740 4)
					view: 7462
					setLoop: 0
					cel: 0
					scaleSignal: 1
					scaleX: 105
					scaleY: 105
					setCycle: End self
				)
			)
			(1
				((ScriptID 740 4) setLoop: 1 setCycle: Fwd)
				(if register (= seconds 3))
			)
			(2
				((ScriptID 740 4)
					setLoop: 0
					setCel: 255
					setCycle: Beg self
				)
			)
			(3
				((ScriptID 740 4)
					view: 7463
					cel: 0
					setLoop: 0
					setScale: 0
					setCycle: End self
				)
			)
			(4 (self init:))
		)
	)
)

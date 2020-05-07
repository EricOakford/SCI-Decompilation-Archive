;;; Sierra Script 1.0 - (do not remove this comment)
(script# 282)
(include sci.sh)
(use Main)
(use KQ6Print)
(use SmallItem)
(use pawnShopGenie)
(use Kq6Procs)
(use PolyPath)
(use StopWalk)
(use Grooper)
(use Motion)
(use System)

(public
	reanimationScr 0
	drinkPotionGenieScr 1
	genieTakeMintScr 2
	genieMirrorScr 3
	genieBadgerOwnerScr 4
	drinkPotionNoGenieScr 5
	givePeppermintScr 6
)

(instance reanimationScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego hide:)
				((ScriptID 280 2)
					posn: 113 144
					view: 282
					loop: 6
					cel: 0
					setScript: 0
				)
				(= ticks 120)
			)
			(1
				(soundFx2 number: 927 loop: 1 play: self)
				(= ticks 30)
			)
			(2 (messager say: 1 0 8 1 self))
			(3 0)
			(4
				(soundFx2 client: 0)
				((ScriptID 280 2) setCycle: End self)
			)
			(5
				((ScriptID 280 2) setCycle: Beg self)
			)
			(6
				((ScriptID 280 2) loop: 2 cel: 0 setCycle: End self)
			)
			(7 (messager say: 1 0 8 2 self))
			(8 (messager say: 1 0 8 3 self))
			(9 (messager say: 1 0 8 4 self))
			(10
				(theMusic number: 240 loop: -1 fade: 70 10 20 0)
				(ego show: posn: 123 140 reset: 3)
				((ScriptID 280 2)
					posn: 135 136
					loop: 7
					cel: 0
					setCycle: End self
				)
			)
			(11 (= cycles 2))
			(12
				((ScriptID 280 2)
					view: 2841
					loop: 0
					cel: 0
					posn: 236 127
					stopUpd:
					setScript: (ScriptID 280 9)
				)
				(theGame handsOn:)
				(theMusic number: 240 loop: -1 play: 0 fade: 70 25 10 0)
				(self dispose:)
				(DisposeScript 282)
			)
		)
	)
)

(instance drinkPotionNoGenieScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 282)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 33 curRoomNum)
				(theGame handsOff:)
				(proc280_10 self)
			)
			(1
				(ego setMotion: PolyPath 156 136 self)
				(theMusic fade: 0 25 10 0)
			)
			(2
				(ego
					normal: 0
					setSpeed: 6
					posn: 146 140
					view: 934
					loop: 0
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(3
				(soundFx2 number: 925 loop: 1 play: self)
			)
			(4 (ego setCycle: End self))
			(5 (= ticks 30))
			(6
				(ego posn: 147 141 loop: 1 cel: 0 setCycle: End self)
				(soundFx number: 926 loop: 1 play:)
			)
			(7
				(messager say: 2 14 10 1 self)
			)
			(8
				((ScriptID 280 2)
					posn: 236 127
					view: 282
					loop: 3
					cel: 0
					setCycle: End self
				)
			)
			(9
				((ScriptID 280 2)
					posn: 171 110
					loop: 4
					cel: 0
					setCycle: End self
				)
			)
			(10 (= ticks 30))
			(11
				(if (!= (soundFx prevSignal?) -1) (-- state))
				(= cycles 2)
			)
			(12
				((ScriptID 280 2)
					posn: 171 110
					loop: 5
					cel: 0
					setCycle: End self
				)
			)
			(13
				(messager say: 2 14 10 2 self)
			)
			(14 (= ticks 45))
			(15
				(soundFx number: 927 loop: 1 play: self)
			)
			(16
				(soundFx client: 0)
				(ego hide:)
				((ScriptID 280 2)
					posn: 113 144
					loop: 6
					cel: 0
					setCycle: End self
				)
			)
			(17
				(messager say: 2 14 10 3 self)
			)
			(18
				((ScriptID 280 2) loop: 2 cel: 0 setCycle: End self)
			)
			(19
				(messager say: 2 14 10 4 self oneOnly: 0)
			)
			(20
				(theMusic number: 240 loop: -1 play: 0 fade: 70 25 10 0)
				(ego show: posn: 123 140 reset: 3)
				((ScriptID 280 2)
					posn: 135 136
					loop: 7
					cel: 0
					setCycle: End self
				)
			)
			(21 (= cycles 2))
			(22
				((ScriptID 280 2)
					view: 2841
					loop: 0
					cel: 0
					posn: 236 127
					stopUpd:
					setScript: (ScriptID 280 9)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance drinkPotionGenieScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 33 curRoomNum)
				(theGame handsOff:)
				(theGame givePoints: 3)
				(proc280_10 self)
			)
			(1
				(Bset 41)
				(Bset 82)
				(messager say: 2 14 9 1 self 280)
				(theMusic fade:)
			)
			(2
				(ego setMotion: PolyPath 156 136 self)
				(theGlobalSound number: 281 loop: -1 play:)
			)
			(3
				(messager say: 2 14 9 2 self 280)
			)
			(4
				(messager say: 2 14 9 3 self 280)
			)
			(5 (proc281_1 self))
			(6
				((ScriptID 281 0)
					view: 289
					posn: 88 129
					setLoop: Grooper
					loop: 3
				)
				(= cycles 2)
			)
			(7
				((ScriptID 281 0) setHeading: 90 self)
			)
			(8
				(ego
					normal: 0
					setSpeed: 6
					posn: 146 140
					view: 2821
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(9
				(messager say: 2 14 9 4 self 280)
			)
			(10
				(messager say: 2 14 9 5 self 280)
			)
			(11
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(12
				(ego
					normal: 0
					setSpeed: 6
					posn: 146 140
					view: 934
					loop: 0
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(13
				(soundFx2 number: 925 loop: 1 play: self)
			)
			(14 (ego setCycle: End self))
			(15
				(theGlobalSound fade:)
				(= ticks 30)
			)
			(16
				(messager say: 2 14 9 6 self 280)
			)
			(17
				(ego posn: 147 141 loop: 1 cel: 0 setCycle: End self)
				(soundFx number: 926 loop: 1 play:)
			)
			(18
				((ScriptID 280 2)
					setPri: -1
					posn: 236 127
					view: 282
					loop: 3
					cel: 0
					setCycle: End self
				)
			)
			(19 (= ticks 120))
			(20
				((ScriptID 280 2)
					posn: 171 110
					setPri: 14
					loop: 4
					cel: 0
					setCycle: End self
				)
			)
			(21
				(if (!= (soundFx prevSignal?) -1) (-- state))
				(= cycles 2)
			)
			(22
				(messager say: 2 14 9 7 self 280)
			)
			(23
				((ScriptID 280 2) loop: 5 cel: 0 setCycle: End self)
			)
			(24
				(messager say: 2 14 9 8 self 280)
			)
			(25
				(proc280_8 0)
				((ScriptID 281 0)
					view: 2833
					loop: 0
					cel: 0
					setCycle: Fwd
				)
				(= ticks 90)
			)
			(26
				((ScriptID 281 0) setCycle: End self)
			)
			(27
				((ScriptID 281 0)
					view: 289
					loop: 0
					cel: 0
					setHeading: 270 self
				)
			)
			(28
				(self setScript: genieExitScr self)
			)
			(29 (= ticks 45))
			(30 (curRoom newRoom: 145))
		)
	)
)

(instance genieMirrorScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 282)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 5 13 0 1 self)
			)
			(1
				(ego setMotion: PolyPath 138 132 self)
			)
			(2
				(ego setHeading: 270 self)
				(proc281_1 self)
			)
			(3 0)
			(4
				(cond 
					((> ((ScriptID 281 0) cel?) 3) ((ScriptID 281 0) setCycle: CT 3 -1 self))
					((< ((ScriptID 281 0) cel?) 3) ((ScriptID 281 0) setCycle: CT 3 1 self))
					(else (= ticks 1))
				)
			)
			(5
				(ego
					setSpeed: 6
					normal: 0
					view: 283
					loop: 5
					cel: 0
					setCycle: End self
				)
			)
			(6
				(messager say: 5 13 0 2 self)
			)
			(7 (ego setCycle: Beg self))
			(8 (= cycles 2))
			(9 (ego reset: 7) (= cycles 2))
			(10
				(proc280_8 0)
				(self setScript: genieExitScr self)
			)
			(11
				(messager say: 5 13 0 3 self oneOnly: 0)
			)
			(12
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance genieExitScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 281 0)
					signal: 16384
					cycleSpeed: 3
					moveSpeed: 3
					view: 289
					setCycle: StopWalk -1
					setMotion: PolyPath 51 124 self
				)
			)
			(1
				((ScriptID 280 3) setPri: 14 setCycle: End self)
				(soundFx2 number: 901 loop: 1 play:)
			)
			(2
				((ScriptID 281 0) setMotion: MoveTo 35 124 self)
			)
			(3
				((ScriptID 281 0) dispose:)
				((ScriptID 280 3) setPri: -1 setCycle: Beg self)
			)
			(4
				(soundFx2 number: 902 loop: 1 play:)
				(= cycles 2)
			)
			(5 (self dispose:))
		)
	)
)

(instance genieTakeMintScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc280_8 0)
				(proc281_1 self)
			)
			(1
				((ScriptID 281 0)
					signal: 16384
					view: 289
					setCycle: Walk
					setMotion: PolyPath 183 132 self
				)
			)
			(2
				(messager say: 4 70 19 10 self)
			)
			(3
				((ScriptID 281 0)
					view: 2834
					setPri: 1
					loop: 0
					cel: 0
					posn: 187 134
					setCycle: End self
				)
			)
			(4
				(UnLoad 128 289)
				((ScriptID 281 0) loop: 1 cel: 0 setCycle: End self)
			)
			(5
				((ScriptID 281 0) cel: 0 setCycle: End self)
			)
			(6
				((ScriptID 281 0) setCycle: Beg self)
			)
			(7
				((ScriptID 281 0)
					cel: ((ScriptID 281 0) lastCel:)
					setCycle: Beg self
				)
			)
			(8 (= cycles 2))
			(9
				(messager say: 4 70 19 11 self)
			)
			(10
				((ScriptID 281 0)
					view: 2835
					loop: 2
					cel: 0
					posn: 190 134
					setCycle: End self
				)
			)
			(11
				(UnLoad 128 2834)
				((ScriptID 281 0)
					posn: 168 133
					setLoop: 3
					cel: 0
					setStep: 4 3
					setCycle: Walk
				)
				(= cycles 2)
			)
			(12
				((ScriptID 281 0) setMotion: PolyPath 51 124 self)
			)
			(13
				((ScriptID 281 0)
					view: 289
					setLoop: -1
					loop: 1
					posn: 51 124
				)
				((ScriptID 280 3) setPri: 14 setCycle: End)
				(soundFx2 number: 901 loop: 1 play:)
				(= ticks 12)
			)
			(14
				(UnLoad 128 2835)
				((ScriptID 281 0) setMotion: MoveTo 35 124 self)
			)
			(15
				((ScriptID 281 0) dispose:)
				((ScriptID 280 3) setCycle: Beg self)
			)
			(16
				(UnLoad 128 289)
				(soundFx2 number: 902 loop: 1 play:)
				(self dispose:)
			)
		)
	)
)

(instance genieBadgerOwnerScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 282)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc280_8 0)
				((ScriptID 281 0)
					view: 289
					loop: 0
					setLoop: Grooper
					cycleSpeed: 6
					setCycle: StopWalk -1
					ignoreActors:
					posn: 192 138
				)
				(= register 0)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo 80 145 self)
			)
			(2 (ego setHeading: 90 self))
			(3 (= cycles 2))
			(4
				(KQ6Print
					font: userFont
					posn: 120 40
					width: 175
					say: 0 1 0 3 1
					init: self
				)
			)
			(5
				(messager say: 1 0 3 2 self 280)
			)
			(6
				(KQ6Print
					font: userFont
					posn: 120 40
					width: 175
					say: 0 1 0 3 3
					init: self
				)
			)
			(7
				(messager say: 1 0 3 4 self 280)
			)
			(8
				(KQ6Print
					font: userFont
					posn: 120 40
					width: 175
					say: 0 1 0 3 5
					init: self
				)
			)
			(9
				((ScriptID 281 0) setHeading: 270 self)
			)
			(10
				(self setScript: genieExitScr self)
			)
			(11 (= cycles 2))
			(12
				((ScriptID 280 2) setScript: (ScriptID 280 9))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance givePeppermintScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 282)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc281_1 self)
			)
			(1
				(messager say: 5 67 0 1 self)
			)
			(2
				(ego setMotion: PolyPath 118 133 self)
			)
			(3
				(ego view: 2832 loop: 0 cel: 0 setSpeed: 6 normal: 0)
				(= cycles 2)
			)
			(4 (ego setCycle: End self))
			(5 (= cycles 2))
			(6
				(messager say: 5 67 0 2 self)
			)
			(7
				(messager say: 5 67 0 3 self)
			)
			(8
				((ScriptID 281 0)
					view: 2834
					loop: 0
					cel: 0
					cycleSpeed: 9
					posn: 92 134
				)
				(= cycles 2)
			)
			(9
				((ScriptID 281 0) setCycle: End self)
			)
			(10
				(ego setCycle: Beg)
				((ScriptID 281 0) loop: 1 cel: 0 setCycle: End self)
			)
			(11
				(ego reset: 1)
				((ScriptID 281 0) cel: 0 setCycle: End self)
			)
			(12
				((ScriptID 281 0) setCycle: Beg self)
			)
			(13
				((ScriptID 281 0)
					cel: ((ScriptID 281 0) lastCel:)
					setCycle: Beg self
				)
			)
			(14
				(messager say: 5 67 0 4 self)
			)
			(15
				((ScriptID 281 0)
					view: 2835
					loop: 2
					cel: 0
					setCycle: End self
				)
			)
			(16
				(proc280_8 0)
				((ScriptID 281 0)
					setLoop: 3
					setCycle: Walk
					posn: 70 133
					setMotion: PolyPath 51 124 self
				)
			)
			(17
				((ScriptID 281 0)
					view: 289
					setLoop: -1
					loop: 1
					posn: 51 124
				)
				((ScriptID 280 3) setPri: 14 setCycle: End)
				(soundFx2 number: 901 loop: 1 play:)
				(= ticks 12)
			)
			(18
				((ScriptID 281 0) setMotion: MoveTo 35 124 self)
			)
			(19
				((ScriptID 281 0) dispose:)
				((ScriptID 280 3) setCycle: Beg self)
			)
			(20
				(soundFx2 number: 902 loop: 1 play:)
				(= cycles 2)
			)
			(21
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

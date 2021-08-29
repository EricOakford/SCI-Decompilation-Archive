;;; Sierra Script 1.0 - (do not remove this comment)
(script# 158)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	brigBlocked 0
	climb 1
)

(instance brigBlocked of Script
	(method (doit)
		(if (and (== state 3) ((ScriptID 95 0) notify: 12))
			(self cue:)
		)
		(if (and (== state 4) ((ScriptID 95 0) notify: 18))
			(self cue:)
		)
		(if (and (< (ego x?) 90) (< state 3))
			(self changeState: 3)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HighPrint 158 0)
				;Oh, a wise guy, eh.
				((ScriptID 95 15) setMotion: MoveTo 91 88 self)
				((ScriptID 95 17) loop: 2 setScript: curlyFollow)
				((ScriptID 95 19) loop: 2 setScript: larryFollow)
			)
			(1
				(if ((ScriptID 95 0) notify: 16)
					(EgoDead 158 1
						#icon vEgoDefeatedMagic 0 9
						#title {Nyuk Nyuk Nyuk -- Mee Mee Mee Mee Mee...}
					)
						;Die drei Knochelkopfen grab you off the table.  You should have waited until they were out of reach.
				else
					((ScriptID 95 15) setMotion: MoveTo 76 106 self)
				)
			)
			(2
				((ScriptID 95 15)
					setPri: 8
					setMotion: MoveTo 100 115 self
				)
			)
			(3
				(if ((ScriptID 95 0) notify: 16)
					((ScriptID 95 0) notify: 15)
				else
					(EgoDead 158 2
						#icon vEgoDefeatedMagic 0 9
						#title {Woo Woo Woo -- Ruff Ruff Ruff.}
					)
						;You're hopelessly surrounded.  You should have taken quicker action against these Knochelkopfs.
						;Elevate your thoughts to a higher plane.
				)
			)
			(4
				((ScriptID 95 15) setMotion: MoveTo 174 124)
			)
			(5
				((ScriptID 95 15)
					view: vBrigandMoe
					setCel: 0
					setLoop: 4
					setMotion: MoveTo 165 124
					setCycle: EndLoop
				)
				((ScriptID 95 16) dispose:)
				((ScriptID 95 0) notify: 21)
				(= cycles 1)
			)
			(6 (self dispose:))
		)
	)
)

(instance curlyFollow of Script
	(method (doit)
		(if (and (== state 4) ((ScriptID 95 0) notify: 20))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 23)
			)
			(1
				((ScriptID 95 17) setMotion: MoveTo 89 88 self)
			)
			(2
				((ScriptID 95 17) setMotion: MoveTo 76 106 self)
			)
			(3
				((ScriptID 95 17)
					setPri: 8
					setMotion: MoveTo 100 115 self
				)
			)
			(4
				((ScriptID 95 17) setMotion: MoveTo 162 124)
			)
			(5
				(= cycles 4)
			)
			(6
				((ScriptID 95 17)
					view: vBrigandCurly
					setCel: 0
					setLoop: 4
					setMotion: MoveTo 153 124
					setCycle: EndLoop
				)
				((ScriptID 95 18) dispose:)
				((ScriptID 95 0) notify: 23)
				(= cycles 1)
			)
			(7
				(self dispose:)
			)
		)
	)
)

(instance larryFollow of Script
	(method (doit)
		(if (and ((ScriptID 95 0) notify: 22) (== state 4))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 158)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 32)
			)
			(1
				((ScriptID 95 19) setMotion: MoveTo 89 88 self)
			)
			(2
				((ScriptID 95 19) setMotion: MoveTo 76 106 self)
			)
			(3
				((ScriptID 95 19)
					setPri: 8
					setMotion: MoveTo 100 115 self
				)
			)
			(4
				((ScriptID 95 19) setMotion: MoveTo 150 124)
			)
			(5
				(= cycles 4)
			)
			(6
				((ScriptID 95 19)
					view: vBrigandLarry
					setCel: 0
					setLoop: 4
					setMotion: MoveTo 144 124
					setCycle: EndLoop
				)
				((ScriptID 95 20) dispose:)
				(= cycles 30)
			)
			(7
				((ScriptID 95 15)
					cycleSpeed: 7
					moveSpeed: 7
					setCycle: BegLoop
					setMotion: MoveTo 164 124
				)
				((ScriptID 95 17)
					cycleSpeed: 7
					moveSpeed: 7
					setCycle: BegLoop
					setMotion: MoveTo 152 124
				)
				((ScriptID 95 19)
					cycleSpeed: 7
					moveSpeed: 7
					setCycle: BegLoop
					setMotion: MoveTo 145 124 self
				)
			)
			(8
				((ScriptID 95 19) view: vBrigandTrio setLoop: 1 setCel: 0)
				((ScriptID 95 4) setScript: (ScriptID 95 24))
				((ScriptID 95 17) dispose:)
				((ScriptID 95 15) dispose:)
				(self dispose:)
			)
		)
	)
)

(instance climb of Script
	(method (doit)
		(if (and ((ScriptID 95 0) notify: 14) (== state 6))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 178 108 self)
			)
			(1
				((ScriptID 95 0) notify: 17)
				(ego
					view: vEgoSwing
					setLoop: 0
					setCel: 0
					setPri: 7
					illegalBits: 0
					posn: 173 108
				)
				(= cycles 2)
			)
			(2
				(ego setCel: 1 posn: 182 97)
				(= cycles 2)
			)
			(3
				(ego setCel: 2 posn: 188 99)
				(= cycles 2)
			)
			(4
				(ego setCel: 3 posn: 191 92)
				(= cycles 2)
			)
			(5
				(ego setCel: 4 posn: 212 100)
				(= cycles 3)
			)
			(6
				((ScriptID 95 0) notify: 13)
				(ego
					view: vEgoStanding
					loop: 1
					setCel: 0
					posn: (- (ego x?) 9) (- (ego y?) 18)
				)
			)
			(7
				(ego
					view: vEgoSwing
					setLoop: 0
					setCel: 5
					posn: 212 100
					cycleSpeed: 3
					setCycle: CycleTo 7 1 self
				)
				((ScriptID 95 6) setLoop: 4 setCel: 0 posn: 211 63)
			)
			(8
				(ego setCel: 8)
				((ScriptID 95 6)
					setLoop: 4
					setCel: 1
					setPri: 11
					posn: 206 50
				)
				(= cycles 3)
			)
			(9
				(ego
					setLoop: 1
					setCel: 0
					setPri: 12
					cycleSpeed: 0
					posn: 230 82
				)
				((ScriptID 95 6) setLoop: 3 setCel: 0 posn: 228 47)
				(= cycles 3)
			)
			(10
				((ScriptID 95 2) setCel: 1 stopUpd:)
				(ego setCel: 1 posn: 244 92)
				((ScriptID 95 6) setCel: 1 posn: 238 57)
				(= cycles 3)
			)
			(11
				(ego setCel: 2 posn: 260 114)
				((ScriptID 95 6) setCel: 2 posn: 246 68)
				(= cycles 3)
			)
			(12
				(ego setCel: 3 posn: 243 128)
				((ScriptID 95 6) setCel: 3 posn: 224 80)
				(= cycles 4)
			)
			(13
				(ego setCel: 4 posn: 183 130)
				((ScriptID 95 6) setCel: 4 posn: 183 88)
				(= cycles 2)
			)
			(14
				((ScriptID 95 0) notify: 19)
				(ego setCel: 5 posn: 119 132)
				((ScriptID 95 6) setCel: 5 posn: 117 88)
				(= cycles 3)
			)
			(15
				(ego setCel: 6 posn: 96 122)
				((ScriptID 95 6) setCel: 6 posn: 93 83)
				(= cycles 3)
			)
			(16
				(ego setCel: 7 posn: 62 106)
				((ScriptID 95 6) setCel: 7 posn: 75 74)
				(= cycles 3)
			)
			(17
				(ego setCel: 8 posn: 47 92)
				((ScriptID 95 6) setCel: 8 posn: 80 60)
				(= cycles 3)
			)
			(18
				((ScriptID 95 2) setCel: 0 stopUpd:)
				(ego setCel: 9 posn: 46 86)
				((ScriptID 95 6) hide:)
				(= cycles 3)
			)
			(19
				(ego setLoop: 2 setCel: 0 posn: 40 88)
				(= cycles 3)
			)
			(20
				(ego setCel: 1 posn: 59 93)
				(= cycles 3)
			)
			(21
				(NormalEgo)
				(if ((ScriptID 95 0) notify: 3)
					(ego illegalBits: (| cWHITE cLRED))
				)
				(ego loop: 2 posn: 63 93)
				(self dispose:)
			)
		)
	)
)

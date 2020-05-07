;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include game.sh)
(use Main)
(use Intrface)
(use Reverse)
(use Motion)
(use Actor)
(use System)

(public
	bottomScript 0
	leftScript 1
	rightScript 2
)

(local
	local0
)
(instance stars of Prop
	(properties)
)

(instance pillow of Prop
	(properties)
)

(instance gerta of Actor
	(properties)
)

(instance bottomScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				((ScriptID 321 7) setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 130 112)
				(= cycles 15)
			)
			(2
				(ego
					setLoop: 3
					setCycle: Reverse
					moveSpeed: 1
					cycleSpeed: 1
					setMotion: MoveTo 130 130 self
				)
			)
			(3
				((ScriptID 321 6)
					setLoop: 0
					posn: 130 112
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 130 120
				)
				(ego
					setLoop: 2
					setCycle: Forward
					moveSpeed: 0
					cycleSpeed: 0
					setStep: 3 3
					setMotion: MoveTo 130 150 self
				)
			)
			(4
				(ego setMotion: MoveTo 130 146 self)
			)
			(5
				(++ local0)
				(ego setMotion: MoveTo 130 150 self)
			)
			(6
				(if (> local0 4)
					(self cue:)
				else
					(self changeState: 4)
				)
			)
			(7
				((ScriptID 321 3) stop:)
				(EgoDead 290 0
					#title { Criminal carelessness._}
					#icon vOttoAsleep 4 0)
					;It's hard to be a hero when you're sitting in a jail cell.
					;The Sheriff apologized for your broken arm, but he did warn you (didn't he?) that Otto was only partly trained.
					;In the future, you'll probably be more careful when you're robbing someone.
			)
		)
	)
)

(instance leftScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 ignoreActors:)
				((ScriptID 321 8) setCycle: EndLoop self)
			)
			(1
				(ego setLoop: 3 setMotion: MoveTo 119 51)
				(= cycles 15)
			)
			(2
				(ego
					setLoop: 3
					setCycle: Reverse
					moveSpeed: 2
					cycleSpeed: 2
					setMotion: MoveTo 119 59 self
				)
			)
			(3
				(Bset SHERIFF_AWAKENED)
				(ego
					view: vEgoBigGrin
					posn: 117 59
					setLoop: 1
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Forward
					setMotion: MoveTo 108 59 self
				)
			)
			(4
				((ScriptID 321 5)
					setLoop: 2
					cel: 0
					posn: 117 48
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 122 58
				)
				(ego setMotion: MoveTo 85 59 self)
			)
			(5
				((ScriptID 321 5)
					setLoop: 1
					cycleSpeed: 0
					moveSpeed: 0
					setMotion: MoveTo 85 58
				)
				(NormalEgo)
				(ego
					illegalBits: 0
					ignoreActors:
					setLoop: 1
					setMotion: MoveTo 80 59 self
				)
			)
			(6
				(ego
					view: vEgoDeadBrigands
					setCycle: Forward
					setLoop: 0
					posn: 79 44
					setStep: 6 9
					setMotion: MoveTo 37 67 self
				)
			)
			(7
				(ego setLoop: 5 setMotion: MoveTo 14 97 self)
			)
			(8
				(ego setLoop: 1 setMotion: MoveTo 20 135 self)
			)
			(9
				((ScriptID 321 5) setLoop: 2)
				(ego setLoop: 3 setCel: 1 setPri: 12 posn: 20 134)
				((View new:)
					view: vEgoDeadBrigands
					loop: 2
					cel: 0
					posn: 19 155
					init:
					stopUpd:
				)
				(stars
					view: vEgoDeadBrigands
					loop: 4
					cel: 0
					posn: 19 135
					init:
					setCycle: Forward
					startUpd:
				)
				(= cycles 2)
			)
			(10
				(ego setCel: 2)
				(++ local0)
				(= cycles 2)
			)
			(11
				(ego setCel: 1)
				(= cycles 2)
			)
			(12
				(if (< local0 4)
					(self changeState: 10)
				else
					(stars dispose:)
					(ego setCel: 0)
					(= cycles 2)
				)
			)
			(13
				((ScriptID 321 3) stop:)
				(EgoDead 290 1
					#title { Criminal carelessness._}
					#icon vEgoDefeated 1 0)
					;When it comes to breaking in, it looks like the only thing broken is your head!
					;When at last you come to your senses, you find yourself in a jail cell.
					;By the time you get out of here, you'll be too old to be a hero.
			)
		)
	)
)

(instance rightScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0)
				((ScriptID 321 9) setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 164 51)
				(= cycles 15)
			)
			(2
				(Print 290 2)
				;Uh Oh! As you see a pillow flying toward you, you hear the Sheriff's wife say...
				(Print 290 3 #at 90 18)
				(pillow
					view: vOttoAsleep
					loop: 6
					cel: 0
					posn: 166 26
					setPri: 1
					init:
					setCycle: Forward
					cycleSpeed: 1
					startUpd:
				)
				(ego setPri: 0)
				(= cycles 12)
				; "SCREEEEEEEEEEEECH!"
			)
			(3
				((ScriptID 321 8) setCycle: EndLoop)
				((ScriptID 321 5)
					setLoop: 2
					cel: 0
					posn: 115 42
					moveSpeed: 1
					setMotion: MoveTo 122 60
				)
				(ego
					setLoop: 3
					setPri: -1
					setCycle: Reverse
					moveSpeed: 2
					cycleSpeed: 2
					setMotion: MoveTo 175 60 self
				)
			)
			(4
				(pillow dispose:)
				((ScriptID 321 8) stopUpd:)
				(ego
					view: vEgoDeadBrigands
					setLoop: 2
					cel: 1
					posn: 175 43
					setCycle: 0
					setPri: 9
				)
				((ScriptID 321 5) setLoop: 4 setCel: 0)
				(= cycles 8)
			)
			(5
				((ScriptID 321 5) setCel: 1 stopUpd:)
				(ego
					view: vEgoDeadBrigands
					setLoop: 1
					cel: 0
					setStep: 4 14
					moveSpeed: 0
					cycleSpeed: 1
					setCycle: CycleTo 1 1 self
				)
			)
			(6
				(ego setPri: 10 setCycle: CycleTo 2 1 self)
			)
			(7
				(ego setCycle: Forward setMotion: MoveTo 175 125 self)
				(gerta
					view: vOttoAsleep
					setLoop: 3
					setCel: 0
					posn: 187 44
					illegalBits: 0
					ignoreActors:
					init:
					setCycle: 0
					setMotion: MoveTo 173 44
				)
			)
			(8
				((ScriptID 321 5) setCel: 2)
				(gerta stopUpd:)
				((ScriptID 321 9) stopUpd:)
				(ego posn: 175 128 setLoop: 6 setCel: 0)
				(stars
					view: vEgoDeadBrigands
					loop: 4
					cel: 0
					posn: 176 141
					init:
					setCycle: Forward
					startUpd:
				)
				(= cycles 2)
			)
			(9
				((ScriptID 321 7)
					loop: 7
					cel: 0
					posn: 109 119
					setPri: 9
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				((ScriptID 321 6)
					setLoop: 1
					cel: 1
					posn: 125 119
					setPri: 8
				)
			)
			(10
				((ScriptID 321 2)
					loop: 1
					number: (SoundFX 15)
					play:
				)
				((View new:)
					view: vSheriffHouse
					loop: 4
					cel: 5
					posn: 117 128
					init:
					stopUpd:
				)
				((View new:)
					view: vSheriffHouse
					loop: 4
					cel: 6
					posn: 139 128
					init:
					stopUpd:
				)
				((ScriptID 321 6)
					cycleSpeed: 1
					moveSpeed: 1
					setPri: 9
					setMotion: MoveTo 135 130 self
				)
			)
			(11
				((ScriptID 321 6) setLoop: 5 setCycle: Forward)
				(= cycles 10)
			)
			(12
				((ScriptID 321 3) stop:)
				(EgoDead 290 4
					#title { Criminal carelessness._}
					#icon vEgoDeadBrigands 6 0)
					;You never dreamed a feather pillow could be so HARD!  Or was it the floor?
					;You also didn't realize how hard it is for a thief to be a Hero.  The Thief of Baghdad should have this kind of luck!
			)
		)
	)
)

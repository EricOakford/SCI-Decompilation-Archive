;;; Sierra Script 1.0 - (do not remove this comment)
(script# 289)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	bustedScript 0
	faceTheMusicScript 1
)

(local
	egoUpstairs
	safeCrackSuccess	;NOTE: this should be a global variable
)
(procedure (BustedPrint)
	(if egoUpstairs
		(CenterPrint &rest)
	else
		(HighPrint &rest)
	)
)

(instance bustedScript of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 289)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: FALSE)
				((ScriptID 321 6)
					setLoop: 0
					cel: 1
					posn: 125 119
					setPri: 8
				)
				((ScriptID 321 5) setLoop: 2 cel: 0 posn: 117 48)
				((ScriptID 321 7)
					loop: 7
					cel: 0
					posn: 109 119
					setPri: 9
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
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
				((ScriptID 321 6) setPri: 9 setMotion: MoveTo 136 140)
				((ScriptID 321 8) setCycle: EndLoop self)
			)
			(2
				((ScriptID 321 5) setMotion: MoveTo 122 60 self)
			)
			(3
				(if (== safeCrackSuccess 2)
					;as this uses a local variable, not a global, this death message is never triggered
					((ScriptID 321 3) stop:)
					(EgoDead 289 0
						#title { Try to stay "in character" next time_}
						#icon vEgoDefeated 1 0
					)
						;Naughty, naughty.  The Sheriff and Otto arrive on the scene and arrest you for "blatant power-gaming".
						;You have to *work* at it to become a *real* hero!
				else
					((ScriptID 321 3) stop:)
					(BustedPrint 289 1)
					;Obviously, you shouldn't have done that!
					(EgoDead 289 2 #title { Criminal carelessness._} #icon vOttoAsleep 4 0)
					;Now you've done it!  It's hard to be a hero when you're locked up for breaking and entering (or entering and breaking, as the case may be).
					;Be a little more intelligent (and a lot quieter) if you ever try something like this again.
				)
			)
		)
	)
)

(instance faceTheMusicScript of Script	
	(method (doit)
		(cond 
			((< (ego distanceTo: (ScriptID 321 6)) 20)
				(self changeState: 9)
			)
			((> (ego y?) 187)
				(User canControl: FALSE)
				(User canInput: FALSE)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 289)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fOttoAwakened)
				(BustedPrint 289 3)
				;Gently and stealthily, you lift the lid on the little box.
				(self cue:)
			)
			(1
				(HandsOff)
				((ScriptID 321 10) setCel: 4)
				(Bset fOpenMusicBox)
				(Bset fOttoClosedMusicBox)
				((ScriptID 321 2) loop: 1 number: 13 play:)
				(= seconds 3)
			)
			(2
				(BustedPrint 289 4 #draw)
				;As the little music box begins to play, you hear the Sheriff yell out: "Otto, stop playing with that music box and GO TO BED!"
				(BustedPrint 289 5)
				;Boy, did YOU make a mistake!
				;
				(ego setMotion: MoveTo (+ (ego x?) 30) (ego y?))
				(= seconds 2)
			)
			(3
				((ScriptID 321 6)
					setLoop: 1
					cel: 1
					posn: 130 118
					setPri: 8
				)
				((ScriptID 321 7) setCycle: EndLoop self)
			)
			(4
				(ego loop: 1)
				((ScriptID 321 6)
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 130 150 self
				)
			)
			(5
				((ScriptID 321 6) setLoop: 5 cel: 0 setCycle: Forward)
				(= cycles 20)
			)
			(6
				(cond 
					((ego has: iMusicBox)
						(BustedPrint 289 6)
						;Otto can't find the music box, but he's too dim and sleepy to figure it out, so he heads on back to bed
					)
					((Btst fOpenMusicBox)
						((ScriptID 321 10) setCel: 3 forceUpd:)
						((ScriptID 321 2) stop:)
						(BustedPrint 289 7 #draw)
						;Otto, even in his sleepy state, winds the music box and closes the lid before he heads back to bed.
						(Bclr fOpenMusicBox)
					)
					(else
						(BustedPrint 289 8)
						;Otto stares sleepily at the closed music box for a moment and heads back to bed.
					)
				)
				((ScriptID 321 6)
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 130 118 self
				)
			)
			(7
				((ScriptID 321 6) posn: 0 1000 stopUpd:)
				((ScriptID 321 7) setCycle: BegLoop self)
			)
			(8
				(BustedPrint 289 9)
				;That was close! The goon must've been so dumb or sleepy or both that he didn't even see you standing there.
				(Bclr fOttoAwakened)
				(HandsOn)
				(ego setScript: 0)
			)
			(9
				((ScriptID 321 3) stop:)
				(EgoDead 289 10
					#title { Criminal carelessness._}
					#icon vEgoDefeated 1 0
				)
					;Obviously, getting in the goon's way was not one of your brighter ideas. You've had it now!
			)
		)
	)
)

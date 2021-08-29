;;; Sierra Script 1.0 - (do not remove this comment)
(script# 294)
(include game.sh)
(use Main)
(use Intrface)
(use Chase)
(use Motion)
(use User)
(use System)

(public
	nextWitch 0
)

(local
	babaSpeaks
)
(instance nextWitch of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 294)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 120 152 self)
			)
			(1
				((ScriptID 21 1) setLoop: 6 posn: 168 122 init:)
				((ScriptID 21 2)
					posn: ((ScriptID 21 1) x?) (- ((ScriptID 21 1) y?) 34)
					init:
					setPri: 15
					setCycle: Forward
				)
				((ScriptID 21 8) play:)
				(= cycles 5)
			)
			(2
				(TimePrint 5 294 0)
				;"Back so soon?"
				((ScriptID 21 3) setCycle: EndLoop)
				((ScriptID 21 4) startUpd:)
				(= babaSpeaks TRUE)
				(= seconds 3)
			)
			(3
				(ego loop: 0)
				((ScriptID 21 2) setCycle: 0)
				(= seconds 2)
			)
			(4
				((ScriptID 21 3) setCycle: 0)
				((ScriptID 21 4) setCycle: 0)
				(Print
					294 1
					#at -1 12
					#width 140
					#mode teJustCenter
					#dispose
					#time 10)
				((ScriptID 21 2) setCycle: Forward)
				(= seconds 7)
				; "Spirits of Mist
; And Creatures of Bog:
; Transform my guest
; To the shape of a Frog."
			)
			(5
				(= babaSpeaks FALSE)
				((ScriptID 21 1) setCycle: EndLoop)
				(= seconds 2)
			)
			(6
				(ego view: vEgoFrogTransform loop: 3 cel: 0 setCycle: EndLoop)
				((ScriptID 21 8) play:)
				((ScriptID 21 2) setCycle: 0 hide:)
				(= cycles 15)
			)
			(7
				((ScriptID 21 4) setCycle: 0)
				((ScriptID 21 1)
					setLoop: 8
					setCycle: Forward
					setMotion: Chase ego 20 self
				)
			)
			(8
				((ScriptID 21 1) setCycle: 0)
				((ScriptID 21 2)
					posn: ((ScriptID 21 1) x?) (- ((ScriptID 21 1) y?) 34)
					setPri: (+ ((ScriptID 21 1) priority?) 1)
					setCycle: Forward
					show:
				)
				(= cycles 2)
			)
			(9
				(= babaSpeaks TRUE)
				(TimePrint 7 294 2)
				;"This I vow: Stay there now!"
				(= seconds 4)
			)
			(10
				(= babaSpeaks FALSE)
				((ScriptID 21 2) setCycle: 0)
				((ScriptID 21 1) setLoop: 6 cel: 0 setCycle: EndLoop)
				(= seconds 3)
			)
			(11
				(= babaSpeaks TRUE)
				(TimePrint 8 294 3)
				;Once again you're a frog, and once again you can't move.  You find it very exasperating!
				(= seconds 8)
			)
			(12
				((ScriptID 21 2) setCycle: Forward)
				(TimePrint 6 294 4)
				;"Yum, Yum.  Froggie Frappe!"
				(= seconds 5)
			)
			(13
				((ScriptID 21 2) setCycle: 0)
				((ScriptID 21 3) setLoop: 5 setCycle: Forward)
				((ScriptID 21 4) setCycle: Forward)
				(= seconds 4)
			)
			(14
				((ScriptID 21 3) setCycle: 0)
				((ScriptID 21 4) setCycle: 0)
				((ScriptID 21 2) setCycle: Forward)
				(TimePrint 8 294 5)
				;"Did you bring me my mandrake like you promised?"
				(= seconds 5)
			)
			(15
				(= babaSpeaks (= seconds 0))
				(= babaState 3)
				((ScriptID 21 2) setCycle: 0)
				((ScriptID 21 1) setScript: (ScriptID 21 7))
				(= yesNoTimer 100)
				(User canInput: TRUE)
			)
			(16
				(= babaSpeaks TRUE)
				(Print
					294 6
					#at -1 130
					#width 250
					#mode teJustCenter
					#dispose
					#time 8)
				(= seconds 8)
				; As you make a feeble croaking sound, you try to nod your head.
			)
			(17
				((ScriptID 21 2) setCycle: Forward)
				(TimePrint 5 294 7)
				;"Well?  Where is it?"
				(= seconds 3)
			)
			(18
				((ScriptID 21 2) setCycle: 0)
				(= seconds 5)
			)
			(19
				((ScriptID 21 2) setLoop: 5 setCycle: Forward)
				(TimePrint 7 294 8)
				;"What's the matter?  Got a frog in your throat?"
				(= seconds 5)
			)
			(20
				((ScriptID 21 3) setCycle: Forward)
				((ScriptID 21 4) setCycle: Forward)
				((ScriptID 21 2) setCycle: 0)
				(= seconds 3)
			)
			(21
				((ScriptID 21 3) setCycle: 0)
				((ScriptID 21 4) setCycle: 0)
				((ScriptID 21 2) setLoop: 4 setCycle: Forward)
				(TimePrint 14 294 9)
				;"I suppose I'll have to turn you back into whatever it is that you were.  Pity!  You're much more appetizing this way."
				(= seconds 12)
			)
			(22
				((ScriptID 21 2) setCycle: 0)
				(= seconds 2)
			)
			(23
				((ScriptID 21 2) setCycle: Forward)
				(Print
					294 10
					#at -1 12
					#width 160
					#mode teJustCenter
					#dispose
					#time 12)
				(= seconds 9)
				; "Creatures of Bog
; And Spirits of Fog:
; Return the true form
; To this rather dumb Frog."
			)
			(24
				(= babaSpeaks FALSE)
				((ScriptID 21 2) setCycle: 0)
				((ScriptID 21 1) setCycle: EndLoop)
				(= seconds 3)
			)
			(25
				((ScriptID 21 8) play:)
				(ego setCycle: BegLoop self)
			)
			(26
				(NormalEgo)
				(ego loop: 0)
				((ScriptID 21 2) setCycle: Forward)
				(= babaSpeaks TRUE)
				(TimePrint 6 294 11)
				;"Now, did you put it in your backpack?"
				(= seconds 5)
			)
			(27
				(= babaSpeaks FALSE)
				((ScriptID 21 2) hide:)
				((ScriptID 21 1) setMotion: Chase ego 10)
				(= cycles 5)
			)
			(28
				(if (ego has: iMandrake)
					((ScriptID 21 1) setPri: 9)
					((ScriptID 21 2)
						posn: ((ScriptID 21 1) x?) (- ((ScriptID 21 1) y?) 34)
						setPri: 10
						setCycle: Forward
						show:
					)
					(= babaSpeaks TRUE)
					(SolvePuzzle f21GiveRoot 3)
					(TimePrint 5 294 12)
					;"Ah!  Here it is."
					(ego use: iMandrake)
					(Bclr fBabaCurse)
					(= seconds 5)
				else
					((ScriptID 21 0) setScript: (ScriptID 21 6))
				)
			)
			(29
				((ScriptID 21 3) setCycle: Forward)
				((ScriptID 21 4) setCycle: Forward)
				((ScriptID 21 2) setLoop: 5)
				(TimePrint 5 294 13)
				;"Kids!  We have it!"
				(= seconds 4)
			)
			(30
				(= babaSpeaks FALSE)
				((ScriptID 21 2) setCycle: 0)
				(= seconds 2)
			)
			(31
				((ScriptID 21 3) setCycle: 0)
				((ScriptID 21 4) setCycle: 0)
				((ScriptID 21 2) hide:)
				((ScriptID 21 1)
					setPri: -1
					setMotion: MoveTo
						(+ ((ScriptID 21 1) x?) 10)
						(- ((ScriptID 21 1) y?) 5)
						self
				)
			)
			(32
				((ScriptID 21 2)
					posn: ((ScriptID 21 1) x?) (- ((ScriptID 21 1) y?) 34)
					setPri: (+ ((ScriptID 21 1) priority?) 1)
					setLoop: 4
					setCycle: Forward
					show:
				)
				(= babaSpeaks TRUE)
				(TimePrint 10 294 14)
				;"That's it!  The final ingredient.  Now we can make our greatest creation..."
				(= seconds 10)
			)
			(33
				((ScriptID 21 3) setCycle: Forward)
				((ScriptID 21 4) setCycle: Forward)
				((ScriptID 21 2) setLoop: 5)
				(TimePrint 4 294 15)
				;"MANDRAKE MOUSSE!"
				(= seconds 4)
			)
			(34
				((ScriptID 21 2) setCycle: 0)
				(= seconds 3)
			)
			(35
				((ScriptID 21 2) setCycle: Forward)
				(TimePrint 10 294 16)
				;"What's that, children?  You think we should reward our lackey here?"
				(= seconds 12)
			)
			(36
				((ScriptID 21 2) setLoop: 4)
				(TimePrint 10 294 17)
				;"Very well, ex-frog.  I'll let you live this time.  Next time, though, it's frog legs for sure!"
				(= seconds 8)
			)
			(37
				((ScriptID 21 2) setCycle: 0 hide:)
				(= seconds 2)
			)
			(38
				(= babaSpeaks FALSE)
				((ScriptID 21 1) setLoop: 6 cel: 0 setCycle: EndLoop self)
				((ScriptID 21 2)
					setLoop: 4
					posn: ((ScriptID 21 1) x?) (- ((ScriptID 21 1) y?) 34)
					setPri: (+ ((ScriptID 21 1) priority?) 1)
					show:
					setCycle: Forward
				)
				(TimePrint 4 294 18)
				;"So...Go!"
			)
			(39
				((ScriptID 21 2) hide:)
				((ScriptID 21 5)
					posn: (ego x?) (ego y?)
					setPri: 15
					init:
					setCycle: CycleTo 6 1 self
				)
				((ScriptID 21 8) play:)
			)
			(40
				(ego hide:)
				((ScriptID 21 5) setCycle: EndLoop)
				(= cycles 15)
			)
			(41
				(client setScript: 0)
				(curRoom newRoom: 22)
			)
		)
	)
)

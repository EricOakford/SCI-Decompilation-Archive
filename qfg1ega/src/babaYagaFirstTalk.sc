;;; Sierra Script 1.0 - (do not remove this comment)
(script# 293)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use User)
(use System)

(public
	firstTalk 0
)

(local
	babaSpeaks
)
(instance firstTalk of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 293)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 21 1)
					setLoop: 0
					setCycle: Forward
					setMotion: MoveTo 119 136 self
				)
			)
			(1
				((ScriptID 21 1) setLoop: 1 setCycle: 0 cel: 0)
				(ego
					view: vBabaYaga1
					setLoop: 2
					cel: 0
					cycleSpeed: 1
					posn: (- ((ScriptID 21 1) x?) 13) (- ((ScriptID 21 1) y?) 25)
					setPri: (+ ((ScriptID 21 1) priority?) 1)
					show:
				)
				((ScriptID 21 4) setCycle: Forward)
				((ScriptID 21 3) setCycle: Forward)
				(= seconds 3)
			)
			(2
				(= babaSpeaks TRUE)
				(TimePrint 4 293 0)
				;"BE STILL!"
				((ScriptID 21 1) setCel: 1)
				((ScriptID 21 2)
					setLoop: 5
					posn: ((ScriptID 21 1) x?) (- ((ScriptID 21 1) y?) 34)
					setPri: (+ ((ScriptID 21 1) priority?) 1)
					setCycle: Forward
					show:
				)
				(= seconds 2)
			)
			(3
				((ScriptID 21 4) setCycle: 0)
				((ScriptID 21 3) setLoop: 1 cel: 4 setCycle: BegLoop)
				(= seconds 2)
			)
			(4
				(TimePrint 6 293 1)
				;"Critics!  We can't all be gourmands, I suppose."
				((ScriptID 21 2) setLoop: 4)
				(= seconds 4)
			)
			(5
				((ScriptID 21 2) setCycle: 0)
				(= seconds 2)
			)
			(6
				((ScriptID 21 1) setCel: 0)
				((ScriptID 21 2) setLoop: 3 setCycle: Forward)
				(TimePrint 7 293 2)
				;Now, Oh-Soon-To-Be-Supper... I don't suppose you have a name?"
				(= seconds 5)
			)
			(7
				(= babaSpeaks (= seconds 0))
				((ScriptID 21 2) setCycle: 0)
				((ScriptID 21 1) setScript: (ScriptID 21 7))
				(= yesNoTimer 100)
				(User canInput: TRUE)
			)
			(8
				(ego setCycle: Forward)
				(= babaSpeaks TRUE)
				(Print
					293 3
					#at -1 130
					#width 250
					#mode teJustCenter
					#dispose
					#time 8)
				(= seconds 8)
				; You try your best to croak out your name, or at least let the witch know that you do indeed have one.
			)
			(9
				(ego setCycle: 0)
				((ScriptID 21 2) setCycle: Forward)
				(= babaSpeaks TRUE)
				(TimePrint 9 293 4)
				;"So you're the one who's trying to be a hero around here.  The only good hero's a dead hero, I always say!"
				(= seconds 7)
			)
			(10
				((ScriptID 21 2) setCycle: 0)
				(= seconds 2)
			)
			(11
				(TimePrint 6 293 5)
				;"But I do have a need for a brave fool.  Are you brave?"
				(= babaState babaBRAVE)
				((ScriptID 21 2) setCycle: Forward)
				(= seconds 4)
			)
			(12
				(= babaSpeaks (= seconds 0))
				((ScriptID 21 2) setCycle: 0)
				((ScriptID 21 1) setScript: (ScriptID 21 7))
				(= yesNoTimer 100)
				(User canInput: TRUE)
			)
			(13
				(ego setCycle: Forward)
				(= babaSpeaks TRUE)
				(Print
					293 6
					#at -1 130
					#width 250
					#mode teJustCenter
					#dispose
					#time 7)
				(= seconds 7)
				; You make little froggy sounds, trying your best to indicate your agreement.
			)
			(14
				(ego setCycle: 0)
				(TimePrint 2 293 7)
				;"Wellll..."
				(= seconds 2)
			)
			(15
				((ScriptID 21 2) setCycle: Forward)
				(TimePrint 8 293 8)
				;"If you're willing to do a small little teensy favor for me, I might reconsider having you for supper."
				(= seconds 6)
			)
			(16
				((ScriptID 21 2) setCycle: 0)
				(= seconds 2)
			)
			(17
				((ScriptID 21 2) setCycle: Forward)
				(TimePrint 7 293 9)
				;"I need the root of a mandrake plant that grows in the graveyard.  Will you be a sweet and fetch me some?"
				;
				(= seconds 8)
			)
			(18
				(= babaSpeaks (= seconds 0))
				(= babaState babaFETCH)
				((ScriptID 21 2) setCycle: 0)
				((ScriptID 21 1) setScript: (ScriptID 21 7))
				(= yesNoTimer 100)
				(User canInput: TRUE)
			)
			(19
				(ego setCycle: Forward)
				(= babaSpeaks TRUE)
				(Print
					293 10
					#at -1 130
					#width 250
					#mode teJustCenter
					#dispose
					#time 7)
				(= seconds 7)
				; "Anything but Frog Legs Fricassee!", you think.   You croak your agreement to the task.
			)
			(20
				(ego setCycle: 0)
				((ScriptID 21 4) setCycle: Forward)
				((ScriptID 21 3) setLoop: 5 setCycle: Forward)
				((ScriptID 21 1) setCel: 1)
				((ScriptID 21 2) setLoop: 4 setCycle: Forward)
				(TimePrint 6 293 11)
				;"And I had my mouth watering for frog.  Oh well!"
				(= seconds 4)
			)
			(21
				((ScriptID 21 1) setCel: 0)
				((ScriptID 21 2) setCycle: 0 setLoop: 3)
				(= seconds 3)
			)
			(22
				((ScriptID 21 4) setCycle: 0)
				((ScriptID 21 3) setCycle: 0)
				((ScriptID 21 2) setCycle: Forward)
				(Print
					293 12
					#at -1 12
					#width 160
					#mode teJustCenter
					#dispose
					#time 9)
				(= seconds 9)
				; "Hear what I say
				; And hear me right:
				; Mandrake must be pulled
				; At precisely Midnight!"
			)
			(23
				(Print
					293 13
					#at -1 12
					#width 160
					#mode teJustCenter
					#dispose
					#time 9)
				(= seconds 9)
				; "This I tell you
				; And this I say:
				; Return with the root
				; Ere the break of next day."
			)
			(24
				(Print
					293 14
					#at -1 12
					#width 145
					#mode teJustCenter
					#dispose
					#time 9)
				(= seconds 9)
				; "Hear what I say
				; And know I don't lie:
				; Bring back the root
				; Or else you will die!!"
			)
			(25
				(= babaSpeaks FALSE)
				((ScriptID 21 2) hide:)
				(ego hide:)
				((ScriptID 21 1)
					setLoop: 0
					setCycle: BegLoop
					setMotion: MoveTo 140 136 self
				)
			)
			(26
				((ScriptID 21 1)
					setLoop: 7
					cel: 5
					setCycle: CycleTo 3 -1 self
				)
			)
			(27
				(ego
					view: vEgoFrogTransform
					setLoop: 3
					cel: 7
					posn: (- ((ScriptID 21 1) x?) 17) (+ ((ScriptID 21 1) y?) 4)
					show:
				)
				((ScriptID 21 1) setCycle: BegLoop self)
			)
			(28
				((ScriptID 21 4) setCycle: Forward)
				((ScriptID 21 3) setCycle: Forward)
				((ScriptID 21 1) setLoop: 6 cel: 0 setCycle: EndLoop self)
				((ScriptID 21 2)
					setLoop: 4
					posn: ((ScriptID 21 1) x?) (- ((ScriptID 21 1) y?) 34)
					setPri: (+ ((ScriptID 21 1) priority?) 1)
					show:
					setCycle: Forward
				)
				(TimePrint 4 293 15)
				;"You, Shoo!"
			)
			(29
				((ScriptID 21 2) hide:)
				((ScriptID 21 5)
					posn: (ego x?) (ego y?)
					setPri: 15
					init:
					setCycle: CycleTo 5 1 self
				)
				((ScriptID 21 8) play:)
			)
			(30
				(ego hide:)
				((ScriptID 21 5) setCycle: EndLoop)
				(= cycles 10)
			)
			(31 (curRoom newRoom: 22))
		)
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Menu)

(public
	rm290 0
)

(local
	doneTime
)
(instance rm290 of Room
	(properties
		picture 99
		style IRISIN
	)
	
	(method (init &tmp speedFile [fileBuf 9])
		(HandsOff)
		(= programControl FALSE)
		(StatusLine disable:)
		(TheMenuBar hide:)
		(super init:)
		(if (!= (= speedFile (FOpen {RESOURCE.LL3} 1)) -1)
			(= filthLevel (ReadNumber (FGets @fileBuf 8 speedFile)))
		)
		(FClose speedFile)
		(ego
			view: 290
			posn: 20 100
			setStep: 1 1
			setMotion: MoveTo 3000 100
			setCycle: Walk
			init:
		)
		(theGame setSpeed: 0)
	)
	
	(method (doit)
		(super doit:)
		;EO: The reason for the speed bugs is that
		; the speed tester didn't account for newer machines.
		; Later games fixed this by using the howFast global,
		; with only a few possible values and an upper limit.
		(if (== (++ machineSpeed) 1)
			(= doneTime (+ 60 (GetTime)))
		)
		(if (< doneTime (GetTime))
			(if debugging
				(theGame setSpeed: 2)
			else
				(theGame setSpeed: 6)
			)
			(= expletive
				(Format @expletiveStr 290 0
					(switch filthLevel
						(4 {Son of a bitch!})
						(3 {Damn it to hell!})
						(2 {Damn!})
						(1 {Gol dang it!})
						(else  {Golly gee!})
					)
				)
			)
			(Format @filthStr 290 0
				(switch filthLevel
					(4 {Totally Raunchiest})
					(3 {Really Filthy})
					(2 {Pretty Dirty})
					(1 {Rather Risque})
					(else  {Mother Goose})
				)
			)
			(if (> (DoSound NumVoices) 3)
				(= musicLoop -1)
			else
				(= musicLoop 1)
			)
			(= global112 0)
			(= minutesBetweenReminders 5)
			(= gameSeconds 0)
			(= gameMinutes 0)
			(= gameHours 0)
			(= printTime 20)
			(= currentEgoView 718)
			(= currentStatus 19)
			(= currentEgo (Format @egoName 290 1))
			(Bclr fAutoSaveDisabled)
			(Bclr fSaveDisabled)
			(TheMenuBar draw:)
			(StatusLine enable:)
			(curRoom newRoom: 200)
		)
	)
)

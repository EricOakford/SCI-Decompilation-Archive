;;; Sierra Script 1.0 - (do not remove this comment)
(script# 318)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Actor)

(public
	rm318 0
)


(local
	rewardHeading1
	rewardHeading2
	rewardHeading3
	wantedHeading
	noticeHeading
	rewardHeading4
	elsaPicture
	barnardPicture
	wantedHeading2
	posterView
	highlightedPoster
)

(enum 1		;posters on the board
   healerRing
   elsa
   warlock
   leader
   spellComponents
   barnard
   exit
)
(procedure (PosterPrint theWidth theString)
	(Print theString
		#at -1 12
		#mode teJustCenter
		#width theWidth
	)
)

(procedure (HighlightPoster)
	(switch highlightedPoster
		(healerRing
			(posterView cel: 0 posn: 60 108 stopUpd:)
		)
		(elsa
			(posterView cel: 1 posn: 120 105 stopUpd:)
		)
		(warlock
			(posterView cel: 0 posn: 197 106 stopUpd:)
		)
		(leader
			(posterView cel: 2 posn: 100 154 stopUpd:)
		)
		(spellComponents
			(posterView cel: 0 posn: 176 155 stopUpd:)
		)
		(barnard
			(posterView cel: 3 posn: 263 146 stopUpd:)
		)
		(exit
			(posterView cel: 4 posn: 247 187 stopUpd:)
		)
	)
)

(procedure (ReadPoster &tmp [rectPoints 4] [str 400])
	(switch highlightedPoster
		(healerRing
			(Format @str 318 0)
			;Reward for return of lost ring. Inquire at the Healer's.
			(PosterPrint 185 @str)
		)
		(elsa
			(Format @str 318 1)
			;This poster is rather dusty and faded. The picture is of a small child with braids.
			;"Reward of 50 gold coins for the safe return of\nElsa von Spielburg.\nInquire at Spielburg Castle gates."
			(PosterPrint 260 @str)
		)
		(warlock
			(Format @str 318 2)
			;Reward of 30 gold coins for the Capture or Death of the Brigand Warlock. Description:  short, ugly, and
			;wears brightly colored robes. Has habit of laughing continually. Inquire at Spielburg Castle gates.
			(PosterPrint 200 @str)
		)
		(leader
			(Format @str 318 3)
			;Wanted:  Brigand Leader. Description:  Unknown appearance, wears a cloak. Must provide proof of leader's identity.
			;Reward of 60 gold coins and title of Hero of the Realm. Inquire at Spielburg Castle gates.
			(PosterPrint 300 @str)
		)
		(spellComponents
			(Format @str 318 4)
			;Notice:  Spell components needed. Cash or trade for potions. Inquire at the Healer's.
			(PosterPrint 200 @str)
		)
		(barnard
			(Bset fReadBarnardBulletin)
			(Format @str 318 5)
			;This poster seems to have been here a while. It has a picture of a handsome, but arrogant young man.
			;"Reward of 50 gold coins for information leading to the return of Baronet Barnard von Spielburg. Inquire at Spielburg Castle gates."
			(PosterPrint 280 @str)
		)
		(exit
			(curRoom newRoom: 311)
		)
	)
)

(instance rm318 of Room
	(properties
		picture 318
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW vNoticeBoard)
		(super init:)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(directionHandler add: self)
		(StatusLine enable:)
		(User canControl: FALSE)
		((= rewardHeading1 (View new:))
			view: vNoticeBoard
			loop: 0
			cel: 1
			posn: 60 76
			setPri: 0
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= rewardHeading2 (View new:))
			view: vNoticeBoard
			loop: 0
			cel: 1
			posn: 120 70
			setPri: 0
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= rewardHeading3 (View new:))
			view: vNoticeBoard
			loop: 0
			cel: 1
			posn: 197 74
			setPri: 0
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= wantedHeading (View new:))
			view: vNoticeBoard
			loop: 0
			cel: 0
			posn: 100 124
			setPri: 0
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= noticeHeading (View new:))
			view: vNoticeBoard
			loop: 0
			cel: 2
			posn: 175 123
			setPri: 0
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= rewardHeading4 (View new:))
			view: vNoticeBoard
			loop: 0
			cel: 1
			posn: 263 104
			setPri: 0
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= elsaPicture (View new:))
			view: vNoticeBoard
			loop: 1
			cel: 0
			posn: 119 84
			setPri: 1
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= barnardPicture (View new:))
			view: vNoticeBoard
			loop: 1
			cel: 1
			posn: 262 120
			setPri: 1
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= wantedHeading2 (View new:))
			view: vNoticeBoard
			loop: 0
			cel: 0
			posn: 100 124
			setPri: 1
			init:
			ignoreActors:
			stopUpd:
			addToPic:
		)
		(= highlightedPoster 1)
		((= posterView (View new:))
			view: vNoticeBoard
			loop: 2
			cel: 0
			posn: 60 108
			setPri: 1
			init:
			ignoreActors:
			stopUpd:
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(User canControl: TRUE)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp thisControl whichPoster)
		(switch (event type?)
			(mouseDown
				(= whichPoster 0)
				(switch (= thisControl (OnControl CMAP (event x?) (event y?)))
					(cBLUE (= whichPoster 1))
					(cGREEN (= whichPoster 2))
					(cCYAN (= whichPoster 3))
					(cRED (= whichPoster 4))
					(cMAGENTA (= whichPoster 5))
					(cBROWN (= whichPoster 6))
					(cYELLOW
						(= highlightedPoster 7)
						(HighlightPoster)
						(RedrawCast)
						(event claimed: TRUE)
						(curRoom newRoom: 311)
					)
				)
				(event claimed: TRUE)
				(HighlightPoster)
				(if whichPoster
					(= highlightedPoster whichPoster)
					(HighlightPoster)
					(RedrawCast)
					(ReadPoster)
				)
			)
			(saidEvent
				(cond 
					((Said 'cease,done,done,done')
						(event claimed: TRUE)
						(curRoom newRoom: 311)
					)
					((Said 'look')
						(event claimed: TRUE)
						(ReadPoster)
					)
				)
			)
			(direction
				(switch (event message?)
					(dirN
						(if (< (-= highlightedPoster 3) 1)
							(+= highlightedPoster 7)
						)
					)
					(dirE
						(if (> (++ highlightedPoster) 7)
							(= highlightedPoster 1)
						)
					)
					(dirS
						(if (> (+= highlightedPoster 3) 7)
							(-= highlightedPoster 7)
						)
					)
					(dirW
						(if (< (-- highlightedPoster) 1)
							(= highlightedPoster 7)
						)
					)
				)
				(event claimed: TRUE)
				(HighlightPoster)
			)
			(keyDown
				(if (== (event message?) ENTER)
					(event claimed: TRUE)
					(ReadPoster)
					(if (> (++ highlightedPoster) 7)
						(= highlightedPoster 1)
					)
					(HighlightPoster)
				)
			)
		)
		(super handleEvent: event)
	)
)

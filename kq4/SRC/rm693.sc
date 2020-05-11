;;; Sierra Script 1.0 - (do not remove this comment)
(script# 693)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room693 0
)

(local
	poof
	hat
)
(instance Room693 of Room
	(properties
		picture 201
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(self setRegions: END)
		((= hat (View new:))
			view: 767
			loop: 0
			cel: 3
			posn: 270 137
			setPri: 15
			init:
		)
		((View new:)
			view: 768
			loop: 0
			cel: 0
			posn: 157 65
			init:
			addToPic:
		)
		((View new:)
			view: 769
			loop: 3
			cel: 0
			posn: 48 139
			init:
			addToPic:
		)
		(ego setScript: egoActions)
	)
)

(instance egoActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Timer setReal: self 2)
				(User canControl: FALSE canInput: FALSE)
			)
			(1
				(= poof (Prop new:))
				(poof
					view: 687
					loop: 0
					cel: 0
					posn: 225 149
					setPri: 15
					ignoreActors:
					setCycle: CycleTo 3 1 self
					init:
				)
			)
			(2
				(ego
					view: 759
					setLoop: -1
					loop: 1
					cel: 2
					posn: 225 149
					init:
				)
				(poof setCycle: EndLoop self)
			)
			(3 (= seconds 2))
			(4
				(if (ego has: iMagicFruit)
					(self changeState: 10)
				else
					(self changeState: 5)
				)
			)
			(5
				(ego setMotion: MoveTo 245 220 self)
			)
			(6 (curRoom newRoom: 694))
			(10
				(ego setCycle: Walk setMotion: MoveTo 253 135 self)
			)
			(11
				(ego loop: 4 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(12
				(hat dispose:)
				(ego setCycle: EndLoop self)
			)
			(13
				(ego
					view: 759
					loop: 1
					setCycle: Walk
					setMotion: MoveTo 245 220 self
				)
			)
			(14 (curRoom newRoom: 694))
		)
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm22 0
)

(local
	bains
	policeTitle
	questTItle
	romanNumTitle
	romanNumTitle2
)
(instance finish of Sound
	(properties
		number 6
	)
)

(instance rm22 of Room
	(properties
		picture 49
		style IRISIN
	)
	
	(method (init)
		(super init:)
		(Load PICTURE 0)
		(Load SOUND 6)
		(Load VIEW 900)
		(Load VIEW 901)
		(Load VIEW 902)
		(Load VIEW 10)
		(Load VIEW 6)
		(Load VIEW 13)
		(Load VIEW 15)
		((= bains (Actor new:))
			view: 13
			posn: 340 100
			setStep: 3 2
			cycleSpeed: 2
			moveSpeed: 2
			setCycle: Walk
			init:
		)
		(ego
			view: 10
			posn: -20 100
			setStep: 3 2
			cycleSpeed: 2
			setCycle: Walk
			moveSpeed: 2
			init:
		)
		(self setScript: rm22Script)
	)
)

(instance rm22Script of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 100 100)
				(bains setMotion: MoveTo 220 100 self)
			)
			(1
				(cSound stop:)
				(finish play:)
				(bains view: 15 setCel: 0)
				(ego view: 6 setCel: 0)
				(= seconds 5)
			)
			(2
				(curRoom drawPic: 0)
				(ego hide:)
				(bains hide:)
				((= questTItle (View new:))
					view: 901
					loop: 0
					posn: 160 112
					setPri: 10
					init:
					ignoreActors:
					stopUpd:
					addToPic:
				)
				((= policeTitle (View new:))
					view: 900
					loop: 0
					posn: 160 63
					setPri: 9
					init:
					ignoreActors:
					stopUpd:
					addToPic:
				)
				((= romanNumTitle (Actor new:))
					view: 902
					loop: 1
					cel: 0
					posn: 143 178
					setPri: 15
					init:
					ignoreActors:
				)
				((= romanNumTitle2 (Actor new:))
					view: 902
					loop: 0
					cel: 0
					posn: 177 178
					setPri: 15
					init:
					ignoreActors:
				)
				(= seconds 10)
			)
			(3
				(curRoom newRoom: 200)
			)
		)
	)
)

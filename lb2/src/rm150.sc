;;; Sierra Script 1.0 - (do not remove this comment)
(script# 150)
(include game.sh)
(use Main)
(use LBRoom)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm150 0
)

(instance rm150 of LBRoom
	(properties
		picture 150
		style FADEOUT
	)
	
	(method (init)
		(LoadMany RES_VIEW 151 150)
		(LoadMany RES_SOUND 150)
		(self setRegions: 92)
		(super init:)
		(theMusic number: 150 flags: mNOPAUSE loop: -1 play:)
		(lauraTrain init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	
	(method (changeState newState &tmp theWidth)
		(switch (= state newState)
			(0
				(lauraTrain setMotion: MoveTo -245 56)
				(= seconds 2)
			)
			(1
				(creditTitle init: setMotion: MoveTo 35 107 self)
				(creditName init: setMotion: MoveTo 35 151 self)
			)
			(2 0)
			(3 (= seconds 3))
			(4
				(= theWidth (CelWide 151 4 0))
				(creditTitle setMotion: MoveTo (- 0 theWidth) 107 self)
				(creditName setMotion: MoveTo -250 151 self)
			)
			(5 0)
			(6 (= seconds 3))
			(7
				(= theWidth (CelWide 151 5 0))
				(creditName
					posn: 590 130
					loop: 5
					setMotion: MoveTo -222 130 self
				)
				(creditTitle
					posn: (- (- (creditName x?) theWidth) 20) 130
					loop: 5
					setMotion: MoveTo (- 0 theWidth) 130 self
				)
			)
			(8 0)
			(9 (= seconds 3))
			(10
				(creditTitle
					posn: -236 119
					loop: 6
					setMotion: MoveTo 42 119 self
				)
				(creditName
					posn: -135 146
					loop: 6
					setMotion: MoveTo 143 146 self
				)
			)
			(11 0)
			(12 (= seconds 3))
			(13
				(= theWidth (CelWide 151 6 0))
				(creditTitle setMotion: MoveTo (- 0 theWidth) 119 self)
				(creditName setMotion: MoveTo -145 146 self)
			)
			(14 0)
			(15
				(curRoom newRoom: 155)
				(self dispose:)
			)
		)
	)
)

(instance lauraTrain of Actor
	(properties
		x 300
		y 56
		view 150
		moveSpeed 10
	)
)

(instance creditTitle of Actor
	(properties
		x 35
		y 200
		view 151
		loop 4
		signal (| ignrAct ignrHrz fixedLoop)
		moveSpeed 0
	)
)

(instance creditName of Actor
	(properties
		x 35
		y 244
		view 151
		loop 4
		cel 1
		signal (| ignrAct ignrHrz fixedLoop)
		moveSpeed 0
	)
)

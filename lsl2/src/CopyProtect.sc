;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Menu)
(use Actor)

(public
	rm10 0
)

(local
	rand
	dress
	face
	hair
	earring
	local5
	phoneNumber
	local7
	local8
	startingRoom
	aBody
	aFace
	aHair
	aEar
)
(instance rm10 of Room
	(properties
		picture 10
		style DISSOLVE
	)
	
	(method (init &tmp num)
		(HandsOff)
		(if debugging
			(if (<= (= startingRoom (GetNumber {Starting Room})) 0)
				(= startingRoom 90)
			else
				(TheMenuBar draw:)
				(StatusLine enable:)
			)
		)
		(Load VIEW 60)
		(super init:)
		((View new:)
			view: 60
			ignoreActors:
			loop: 4
			cel: 0
			posn: 72 51
			setPri: 1
			addToPic:
		)
		((View new:)
			view: 60
			ignoreActors:
			loop: 4
			cel: 1
			posn: 122 35
			setPri: 0
			addToPic:
		)
		((View new:)
			view: 60
			ignoreActors:
			loop: 4
			cel: 2
			posn: 141 34
			setPri: 0
			addToPic:
		)
		((View new:)
			view: 60
			ignoreActors:
			loop: 4
			cel: 3
			posn: 161 34
			setPri: 0
			addToPic:
		)
		((View new:)
			view: 60
			ignoreActors:
			loop: 4
			cel: 4
			posn: 190 34
			setPri: 0
			addToPic:
		)
		((View new:)
			view: 60
			ignoreActors:
			loop: 4
			cel: 4
			posn: 221 34
			setPri: 0
			addToPic:
		)
		((View new:)
			view: 60
			ignoreActors:
			loop: 4
			cel: 5
			posn: 252 34
			setPri: 0
			addToPic:
		)
		((View new:)
			view: 60
			ignoreActors:
			loop: 5
			cel: 0
			posn: 13 112
			setPri: 7
			addToPic:
		)
		((View new:)
			view: 60
			ignoreActors:
			loop: 6
			cel: 0
			posn: 306 112
			setPri: 7
			addToPic:
		)
		((= aBody (View new:))
			view: 60
			loop: 0
			cel: 0
			setPri: 1
			posn: 154 980
			init:
		)
		((= aFace (View new:))
			view: 60
			loop: 1
			cel: 5
			setPri: 2
			posn: 154 980
			init:
		)
		((= aHair (View new:))
			view: 60
			loop: 2
			cel: 1
			setPri: 3
			posn: 154 980
			init:
		)
		((= aEar (View new:))
			view: 60
			loop: 3
			cel: 4
			setPri: 4
			posn: 154 980
			init:
		)
		(switch (= rand (Random 1 16))
			(1
				(= dress 0)
				(= face 5)
				(= hair 1)
				(= earring 4)
				(= local5 7)
				(= phoneNumber {555-7448})
			)
			(2
				(= dress 1)
				(= face 5)
				(= hair 2)
				(= earring 4)
				(= local5 6)
				(= phoneNumber {555-5968})
			)
			(3
				(= dress 2)
				(= face 5)
				(= hair 3)
				(= earring 1)
				(= local5 8)
				(= phoneNumber {555-3425})
			)
			(4
				(= dress 1)
				(= face 2)
				(= hair 2)
				(= earring 4)
				(= local5 8)
				(= phoneNumber {555-8487})
			)
			(5
				(= dress 2)
				(= face 1)
				(= hair 1)
				(= earring 2)
				(= local5 11)
				(= phoneNumber {555-3787})
			)
			(6
				(= dress 0)
				(= face 4)
				(= hair 2)
				(= earring 4)
				(= local5 6)
				(= phoneNumber {555-2737})
			)
			(7
				(= dress 0)
				(= face 3)
				(= hair 3)
				(= earring 1)
				(= local5 12)
				(= phoneNumber {555-2867})
			)
			(8
				(= dress 1)
				(= face 1)
				(= hair 1)
				(= earring 4)
				(= local5 11)
				(= phoneNumber {555-3825})
			)
			(9
				(= dress 2)
				(= face 0)
				(= hair 0)
				(= earring 3)
				(= local5 14)
				(= phoneNumber {555-2868})
			)
			(10
				(= dress 1)
				(= face 3)
				(= hair 2)
				(= earring 4)
				(= local5 12)
				(= phoneNumber {555-3425})
			)
			(11
				(= dress 0)
				(= face 5)
				(= hair 0)
				(= earring 0)
				(= local5 6)
				(= phoneNumber {555-3642})
			)
			(12
				(= dress 2)
				(= face 2)
				(= hair 2)
				(= earring 3)
				(= local5 8)
				(= phoneNumber {555-5464})
			)
			(13
				(= dress 1)
				(= face 1)
				(= hair 3)
				(= earring 5)
				(= local5 14)
				(= phoneNumber {555-6262})
			)
			(14
				(= dress 0)
				(= face 1)
				(= hair 1)
				(= earring 0)
				(= local5 14)
				(= phoneNumber {555-8627})
			)
			(15
				(= dress 2)
				(= face 4)
				(= hair 0)
				(= earring 2)
				(= local5 6)
				(= phoneNumber {555-5633})
			)
			(16
				(= dress 1)
				(= face 1)
				(= hair 3)
				(= earring 2)
				(= local5 13)
				(= phoneNumber {555-5834})
			)
		)
		(aBody posn: 154 80 setLoop: 0 setCel: dress)
		(aFace posn: 154 80 setLoop: 1 setCel: face)
		(aHair posn: 154 80 setLoop: 2 setCel: hair)
		(aEar posn: 154 80 setLoop: 3 setCel: earring)
		(RedrawCast)
		(Display 10 0
			p_at 60 176
			p_color vBLUE
			p_back vCYAN
		)
	)
	
	(method (doit &tmp num)
		(if (and debugging (!= startingRoom 10))
			(self newRoom: startingRoom)
			(return)
		)
		(Format @str 10 1)
		(Print 10 2
			#at -1 144
			#width 248
			#font smallFont
			#edit @str 11
		)
		(cls)
		(= num (ReadNumber @str))
		(cond 
			((not (StrCmp @str {555-0724}))
				(= debugging TRUE)
				(curRoom newRoom: 23)
			)
			((not (StrCmp @str phoneNumber))
				(curRoom newRoom: 90)
			)
			(else
				(Print 10 3)
				(if local8
					(Print 10 4)
				else
					(= quit TRUE)
				)
			)
		)
	)
)

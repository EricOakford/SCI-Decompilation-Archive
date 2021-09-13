;;; Sierra Script 1.0 - (do not remove this comment)
(script# COPYPROTECT)
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
)
(instance rm10 of Room
	(properties
		picture 10
		;style DISSOLVE
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
		(aView1 ignoreActors: loop: 4 cel: 0 setPri: 1 addToPic:)
		(aView2 ignoreActors: loop: 4 cel: 1 setPri: 0 addToPic:)
		(aView3 ignoreActors: loop: 4 cel: 2 setPri: 0 addToPic:)
		(aView4 ignoreActors: loop: 4 cel: 3 setPri: 0 addToPic:)
		(aView5 ignoreActors: loop: 4 cel: 4 setPri: 0 addToPic:)
		(aView6 ignoreActors: loop: 4 cel: 4 setPri: 0 addToPic:)
		(aView7 ignoreActors: loop: 4 cel: 5 setPri: 0 addToPic:)
		(aView8 ignoreActors: loop: 5 cel: 0 setPri: 7 addToPic:)
		(aView9 ignoreActors: loop: 6 cel: 0 setPri: 7 addToPic:)
		(aBody loop: 0 cel: 0 setPri: 1 init:)
		(aFace loop: 1 cel: 5 setPri: 2 init:)
		(aHair loop: 2 cel: 1 setPri: 3 init:)
		(aEar loop: 3 cel: 4 setPri: 4 init:)
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
			;Amiga addition
			((not (StrCmp @str {555-7915}))
				(curRoom newRoom: 90)
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

(instance aView1 of View
	(properties
		y 51
		x 72
		view 60
	)
)

(instance aView2 of View
	(properties
		y 35
		x 122
		view 60
	)
)

(instance aView3 of View
	(properties
		y 34
		x 141
		view 60
	)
)

(instance aView4 of View
	(properties
		y 34
		x 161
		view 60
	)
)

(instance aView5 of View
	(properties
		y 34
		x 190
		view 60
	)
)

(instance aView6 of View
	(properties
		y 34
		x 221
		view 60
	)
)

(instance aView7 of View
	(properties
		y 34
		x 252
		view 60
	)
)

(instance aView8 of View
	(properties
		y 112
		x 13
		view 60
	)
)

(instance aView9 of View
	(properties
		y 112
		x 306
		view 60
	)
)

(instance aBody of View
	(properties
		y 980
		x 154
		view 60
	)
)

(instance aFace of View
	(properties
		y 980
		x 154
		view 60
	)
)

(instance aHair of View
	(properties
		y 980
		x 154
		view 60
	)
)

(instance aEar of View
	(properties
		y 980
		x 154
		view 60
	)
)

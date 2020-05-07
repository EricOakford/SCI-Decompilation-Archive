;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40)
(include sci.sh)
(use Game)
(use System)

(public
	rWonder 0
)

(class rWonder of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		spiderBit 0
		parchmentBit 0
		gotParchment 0
		tomoTalk 0
		stickTalk 0
		grapeTalk 0
		vineTalk 0
		holeLooks 0
		holeGrabs 0
		oysterDozing 0
		flowerDance 0
		babyFed 0
		lampMsg 0
		bottleSucker 0
		alexX 0
		alexY 0
		alexInvisible 0
		grabAtHidingHole 0
	)
	
	(method (newRoom n)
		(= keep (OneOf n 450 460 461 470 475 480 490))
		(= initialized 0)
		(super newRoom: n &rest)
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm41 0
)

(local
	local0
	bikiniInRoom
	canFollowHenchwoman
	henchwomanApproaches
)
(instance mWhistle of Sound
	(properties
		number 30
		priority 100
	)
)

(instance rm41 of Room
	(properties
		picture 41
		horizon 5
	)
	
	(method (init)
		(Load VIEW 401)
		(Load VIEW 403)
		(Load SOUND 30)
		(super init:)
		(mWhistle init:)
		(addToPics
			add:
				aNude1
				aNude2
				aNude3
				aNude4
				aNude5
				aNude6
				aNude7
				aNude8
				aNude9
				aNude10
			doit:
		)
		(if (< (ego y?) 164) (ego y: 164))
		(NormalEgo)
		(ego init:)
		(self setRegions: BEACH setScript: rm41Script)
		(cond 
			(
				(and
					((inventory at: iBikiniBottom) ownedBy: curRoomNum)
					(== currentEgoView 149)
				)
				(= bikiniInRoom TRUE)
				(Load VIEW 400)
				(aBikini
					stopUpd:
					init:
				)
			)
			((== currentEgoView vEgo)
				(self setRegions: HENCHWOMAN)
				(= henchwomanIsHere TRUE)
				(= henchView 402)
				(Load VIEW henchView)
				(aHench
					view: henchView
					setPri: 12
					illegalBits: 0
					cycleSpeed: 2
					init:
					setScript: henchScript
				)
				(NotifyScript HENCHWOMAN 1)
			)
		)
	)
)

(instance rm41Script of Script
	(method (doit)
		(super doit:)
		(if (== EAST (ego edgeHit?))
			(if (== henchwomanApproaches FALSE)
				(curRoom newRoom: 42)
			else
				(Print 41 0 #at 15 -1 #width 280)
				(= currentStatus egoCAPTURED)
				(curRoom newRoom: 95)
			)
		)
		(if (and henchwomanIsHere canFollowHenchwoman (> (ego x?) 300))
			(= canFollowHenchwoman FALSE)
			(= henchwomanApproaches TRUE)
			(curRoom east: 95)
			(Print 41 1)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'climb[<over]/boulder')
			(Print 41 2)
		)
		(if (Said 'look>')
			(if (and henchwomanIsHere (Said '/bimbo'))
				(Print 41 3)
			)
			(if (Said '/children,man,bed,bimbo')
				(Print 41 4 #at -1 130 #font smallFont)
			)
			(if (Said '/palm')
				(Print 41 5)
				(if (> filthLevel 13)
					(Print 41 6 #at -1 130)
				)
			)
			(if (Said '/boulder')
				(Print 41 7)
				(if bikiniInRoom
					(Print 41 8)
				)
			)
			(if (Said '[/airport,boulder,beach]')
				(Print 41 9)
				(if bikiniInRoom
					(Print 41 8)
				)
			)
		)
		(if henchwomanIsHere
			(if (Said 'get/towel')
				(Print 41 10)
			)
			(if (Said 'call/bimbo')
				(if (not (ego inRect: 75 150 160 189))
					(NotClose)
				else
					(Print (Format @str 41 11 introductoryPhrase))
					(if (not (henchScript state?))
						(henchScript changeState: 4)
					)
				)
			)
		)
		(if
			(or
				(Said 'wear,(alter<in),(conceal<on)/job,(bra<bathing),bikini')
				(Said 'alter,(get<off),drain/bra,bra')
				(Said 'wear,(conceal<on)/job,panties,bottom,(bra<bathing),bikini')	;EO: fixed said spec
				(Said 'get<naked')
				(Said 'naked')
				(Said 'alter,(get<off),drain/bra,bra')
			)
			(Print 41 12)
			(Print 41 13)
		)
		(if (Said 'get/bottom,bikini,(bottom<bikini)')
			(cond 
				((not bikiniInRoom)
					(Print 41 14)
				)
				((not (ego inRect: 76 179 106 189))
					(NotClose)
				)
				(else
					(Print 41 15)
					(= bikiniInRoom FALSE)
					(ego get: iBikiniBottom)
					(aBikini dispose:)
					(theGame changeScore: 4)
				)
			)
		)
	)
)

(instance henchScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles (Random 30 50))
			)
			(1
				(aHench setCycle: Forward)
				(= cycles (Random 5 22))
			)
			(2
				(aHench setCel:)
				(= seconds 5)
			)
			(3
				(switch (Random 1 3)
					(1 (Print 41 16))
					(2 (Print 41 17))
					(3 (Print 41 18))
				)
				(mWhistle play:)
				(Print 41 19 #at -1 130)
				(self changeState: 0)
			)
			(4
				(= cycles 0)
				(aHench setCel: 0)
				(Print 41 20 #at 15 -1 #width 280 #draw)
				(= seconds 5)
			)
			(5
				(aHench
					setAvoider: (Avoider new:)
					setStep: 3 2
					setLoop: -1
					setPri: -1
					cycleSpeed: 0
					loop: 0
					posn: 105 182
					setCycle: Walk
					setAvoider: Avoider
					setMotion: MoveTo 333 183 self
				)
				(= cycles 20)
			)
			(6
				(if (> (aHench x?) (ego x?))
					(Print 41 21)
				else
					(Print 41 22)
				)
				(= canFollowHenchwoman TRUE)
			)
			(7
				(= seconds 10)
			)
			(8
				(aHench dispose:)
				(= henchView 0)
				(= henchwomanIsHere FALSE)
				(= canFollowHenchwoman FALSE)
			)
		)
	)
)

(instance aNude1 of PicView
	(properties
		y 110
		x 34
		view 401
		priority 7
	)
)

(instance aNude2 of PicView
	(properties
		y 147
		x 77
		view 401
		loop 1
		priority 11
	)
)

(instance aNude3 of PicView
	(properties
		y 103
		x 119
		view 401
		loop 2
		priority 6
	)
)

(instance aNude4 of PicView
	(properties
		y 81
		x 88
		view 401
		loop 2
		cel 1
		priority 4
	)
)

(instance aNude5 of PicView
	(properties
		y 79
		x 10
		view 401
		loop 3
		cel 1
		priority 4
	)
)

(instance aNude6 of PicView
	(properties
		y 108
		x 41
		view 401
		loop 3
		priority 7
	)
)

(instance aNude7 of PicView
	(properties
		y 135
		x 86
		view 403
		priority 9
	)
)

(instance aNude8 of PicView
	(properties
		y 69
		x 58
		view 403
		cel 1
		priority 3
	)
)

(instance aNude9 of PicView
	(properties
		y 108
		x 22
		view 403
		loop 1
		priority 7
	)
)

(instance aNude10 of PicView
	(properties
		y 98
		x 94
		view 403
		loop 2
		priority 6
	)
)

(instance aBikini of View
	(properties
		y 158
		x 88
		view 400
		signal ignrAct
	)
)

(instance aHench of Actor
	(properties
		y 155
		x 106
		loop 4
	)
)

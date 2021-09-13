;;; Sierra Script 1.0 - (do not remove this comment)
(script# 38)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm38 0
)

(local
	lifeboatIsReady
	numClouds
)
(instance rm38 of Rm
	(properties
		picture 38
		east 31
	)
	
	(method (init &tmp temp0 temp1)
		(Load rsVIEW currentEgoView)
		(Load rsVIEW 141)
		(Load rsVIEW 620)
		(super init:)
		(cond 
			((> machineSpeed 60) (= numClouds 3))
			((> machineSpeed 40) (= numClouds 2))
			((> machineSpeed 20) (= numClouds 1))
		)
		(if (not lifeboatLeverPulled)
			(= temp0 0)
			(while (< temp0 numClouds)
				(aClouds
					setPri: 1
					setStep: 1 1
					cel: (Random 0 10)
					ignoreHorizon:
					illegalBits: 0
					setScript: (cloudScript new:)
				)
				(++ temp0)
			)
		)
		(NormalEgo)
		(ego posn: 318 107 init:)
		(self setRegions: 300 setScript: rm38Script)
	)
)

(instance rm38Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				lifeboatLeverPulled
				(< (ego x?) 300)
				(not lifeboatIsReady)
			)
			(= lifeboatIsReady 1)
			(ego observeControl: 16384)
			(Print 38 0)
			(Print 38 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(2
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 165 111 self)
			)
			(3
				(ego view: 141 setLoop: 0 cel: 0 setCycle: Fwd)
				(= cycles (* 3 (NumCels ego)))
			)
			(4
				(ego setLoop: 1 setCycle: Fwd)
				(= cycles (* 3 (NumCels ego)))
			)
			(5
				(ego setLoop: 2 cel: 0 posn: 157 79 setCycle: CT 3 1 self)
			)
			(6
				(ego setPri: 0 setCycle: End self)
			)
			(7
				(if lifeboatLeverPulled
					(curRoom newRoom: 131)
				else
					(User canInput: 1)
					(= currentStatus 1009)
					(= seconds 7)
				)
			)
			(8
				(Print 38 19 #at -1 130)
				(= seconds 7)
			)
			(9 (Print 38 20 #at -1 130))
			(10
				(User canInput: 0)
				(= cycles (= seconds 0))
				(ego setCel: 255 setCycle: CT 3 969 self)
			)
			(11
				(ego setPri: -1 setCycle: Beg self)
			)
			(12
				(ego posn: 165 111)
				(NormalEgo 3)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'explore,(look<in)/boat')
			(cond 
				((== currentStatus 1009) (Print 38 2))
				((!= currentStatus 0) (NotNow))
				(else (Print 38 3))
			)
		)
		(if
			(or
				(Said 'cut/bathing')
				(Said 'apply/apparatus')
				(Said 'boat,decrease/boat')
			)
			(Print 38 4)
			(Print 38 5)
		)
		(if (Said 'look>')
			(if (Said '/lagoon,fluid,lagoon')
				(Print 38 6)
				(Print 38 7 #at -1 130)
			)
			(if (Said '/chain') (Print 38 8))
			(if (Said '/boat,apparatus') (Print 38 9))
			(if (Said '[/airport]') (Print 38 10))
		)
		(if (Said 'get,jerk,(get<off),drain/door,chain')
			(Print 38 11)
		)
		(if (Said 'crawl/chain,balcony')
			(Print 38 12)
			(ego hide:)
			(Print 38 13 #draw)
			(Print 38 14 #at -1 130)
			(= currentStatus 1001)
		)
		(if (Said '(hop<off),dive,hop/overboard,craft')
			(Print 38 12)
			(ego hide:)
			(Print 38 15 #draw)
			(Print 38 14 #at -1 130)
			(= currentStatus 1001)
		)
		(if
			(and
				(== currentStatus 1009)
				(or
					(Said 'hop,new,new[/down,boat,barstool]')
					(Said 'disembark,board')
					(Said 'climb,new,get<off,up')
				)
			)
			(Ok)
			(self changeState: 10)
		)
		(if
			(or
				(Said 'hop,bath[/down,boat,barstool]')
				(Said 'climb/chain')
				(Said 'get,climb<in/boat')
				(Said 'board/boat')
			)
			(cond 
				((!= currentEgoView 100) (Print 38 16))
				((== currentStatus 1009) (Print 38 17))
				((!= currentStatus 0) (NotNow))
				((not (ego inRect: 128 99 217 122)) (NotClose))
				(else
					(if (not boardedLifeboat)
						(= boardedLifeboat 1)
						(theGame changeScore: 2)
					)
					(Ok)
					(self changeState: 2)
				)
			)
		)
		(if (Said 'get/boat') (Print 38 18))
	)
)

(instance cloudScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(client posn: (Random 0 270) (Random 3 35) init:)
				(self changeState: 2)
			)
			(1
				(= temp0 (Random 3 35))
				(= temp1 (Random 0 10))
				(client setCel: temp1 posn: 321 temp0)
				(self changeState: 2)
			)
			(2
				(client
					moveSpeed: (Random 0 3)
					setMotion: MoveTo (- 1 (CelWide 620 0 temp1)) (client y?) self
				)
				(= state 0)
			)
		)
	)
)

(instance aClouds of Act
	(properties
		view 620
		signal $4000
	)
)

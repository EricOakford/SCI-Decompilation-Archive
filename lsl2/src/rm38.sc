;;; Sierra Script 1.0 - (do not remove this comment)
(script# 38)
(include game.sh)
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
(instance rm38 of Room
	(properties
		picture 38
		east 31
	)
	
	(method (init &tmp i temp1)
		(Load VIEW currentEgoView)
		(Load VIEW 141)
		(Load VIEW 620)
		(super init:)
		(cond 
			((> machineSpeed 60) (= numClouds 3))
			((> machineSpeed 40) (= numClouds 2))
			((> machineSpeed 20) (= numClouds 1))
		)
		(if (not lifeboatLeverPulled)
			(= i 0)
			(while (< i numClouds)
				((Actor new:)
					view: 620
					setLoop: 0
					setPri: 1
					setStep: 1 1
					cel: (Random 0 10)
					ignoreHorizon:
					ignoreActors:
					illegalBits: 0
					setScript: (cloudScript new:)
				)
				(++ i)
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
		(if (and lifeboatLeverPulled (< (ego x?) 300) (not lifeboatIsReady))
			(= lifeboatIsReady 1)
			(ego observeControl: cYELLOW)
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
				(ego view: 141 setLoop: 0 cel: 0 setCycle: Forward)
				(= cycles (* 3 (NumCels ego)))
			)
			(4
				(ego setLoop: 1 setCycle: Forward)
				(= cycles (* 3 (NumCels ego)))
			)
			(5
				(ego setLoop: 2 cel: 0 posn: 157 79 setCycle: CycleTo 3 1 self)
			)
			(6
				(ego setPri: 0 setCycle: EndLoop self)
			)
			(7
				(if lifeboatLeverPulled
					(curRoom newRoom: 131)
				else
					(User canInput: TRUE)
					(= currentStatus egoSITTING)
					(= seconds 7)
				)
			)
			(8
				(Print 38 19 #at -1 152)
				(= seconds 7)
			)
			(9 (Print 38 20 #at -1 152))
			(10
				(User canInput: FALSE)
				(= cycles (= seconds 0))
				(ego setCel: 255 setCycle: CycleTo 3 -1 self)
			)
			(11
				(ego setPri: -1 setCycle: BegLoop self)
			)
			(12
				(ego posn: 165 111)
				(NormalEgo 3)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'explore,(look<in)/boat')
			(cond 
				((== currentStatus egoSITTING) (Print 38 2))
				((!= currentStatus egoNORMAL) (NotNow))
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
				(Print 38 7 #at -1 152)
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
			(Print 38 14 #at -1 152)
			(= currentStatus egoDEAD)
		)
		(if (Said '(hop<off),dive,hop/overboard,craft')
			(Print 38 12)
			(ego hide:)
			(Print 38 15 #draw)
			(Print 38 14 #at -1 152)
			(= currentStatus egoDEAD)
		)
		(if
			(and
				(== currentStatus egoSITTING)
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
				((== currentStatus egoSITTING) (Print 38 17))
				((!= currentStatus egoNORMAL) (NotNow))
				((not (ego inRect: 128 99 217 122)) (NotClose))
				(else
					(if (not boardedLifeboat)
						(= boardedLifeboat TRUE)
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
	
	(method (changeState newState &tmp theY theCel)
		(switch (= state newState)
			(0
				(client posn: (Random 0 270) (Random 3 35) init:)
				(self changeState: 2)
			)
			(1
				(= theY (Random 3 35))
				(= theCel (Random 0 10))
				(client setCel: theCel posn: 321 theY)
				(self changeState: 2)
			)
			(2
				(client
					moveSpeed: (Random 0 3)
					setMotion: MoveTo (- 1 (CelWide 620 0 theCel)) (client y?) self
				)
				(= state 0)
			)
		)
	)
)

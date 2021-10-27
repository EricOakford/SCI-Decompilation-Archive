;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm28 0
)
(synonyms
	(body roberts)
)

(local
	local0
	local1
)
(procedure (localproc_000c)
	(return
		(if (ego has: 10)
			(return 1)
		else
			(Print 28 0)
			(return 0)
		)
	)
)

(instance woodyBody of View
	(properties)
)

(instance Wnote of Actor
	(properties)
)

(instance rm28 of Room
	(properties
		picture 28
		style $0000
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(User canInput: 1)
		(Load rsVIEW 267)
		(self setLocales: 153)
		(if (== global182 0) (= local0 1400))
		(Bclr 153)
		(self setScript: trunkScript)
	)
	
	(method (dispose)
		(HandsOn)
		(super dispose:)
	)
)

(instance trunkScript of Script
	(properties)
	
	(method (doit)
		(if (> local0 1) (-- local0))
		(if (== local0 1)
			(Print 28 1 #draw #at -1 50)
			(self changeState: 3)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst 149))
					(cSound number: 12 loop: 1 play:)
					(Bset 149)
				)
				(cond 
					((not (= local1 (Btst 9))) (woodyBody view: 267 cel: 0 loop: 0 posn: 155 111 init:))
					((InRoom 6) (Wnote view: 267 cel: 0 loop: 1 posn: 165 125 init:))
				)
				((View new:)
					view: 254
					loop: 0
					cel: 0
					posn: 154 139
					setPri: 15
					init:
					stopUpd:
					addToPic:
				)
				((View new:)
					view: 254
					loop: 0
					cel: 1
					posn: 156 51
					setPri: 15
					init:
					stopUpd:
					addToPic:
				)
			)
			(1
				(User canInput: 0)
				(Wnote setMotion: MoveTo 165 111 self)
			)
			(2
				(SolvePuzzle 2)
				(Wnote dispose:)
				(ego get: 6)
				((inventory at: 6) showSelf:)
				(User canInput: 1)
			)
			(3 (curRoom newRoom: 27))
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look,frisk/trunk,bottom')
						(cond 
							((not local1) (Print 28 2))
							((InRoom 6) (Print 28 3))
							(else (Print 28 4))
						)
					)
					((Said 'drink/blood') (Print 28 5))
					((Said '/finger') (Print 28 6))
					((or (Said 'use/camera') (Said 'get/painting'))
						(if (localproc_000c)
							(global124 forceUpd: setPri: 0)
							(SolvePuzzle 1 116)
							(Print 28 7 #draw)
							(global124 setPri: 14)
						)
					)
					(
						(or
							(Said 'get,remove,pick/sample,blood')
							(Said 'use/dropper,vial')
							(Said 'deposit/blood/vial')
						)
						(if (localproc_000c)
							(if (Btst 143)
								(Print 28 8)
							else
								(global119 startUpd: setPri: 0)
								(global118 startUpd: setPri: 0)
								(SolvePuzzle 1)
								(Print 28 9 #draw)
								(ego get: 28)
								(Bset 143)
								(global119 setPri: 13 stopUpd:)
								(Print 28 10 #draw)
							)
						)
					)
					(
						(or
							(Said 'use<(cast,plaster)')
							(Said 'make/plaster,cast')
							(Said 'get,cast/print,footprint,footprint')
						)
						(if (localproc_000c) (Print 28 11))
					)
					(
						(or
							(Said 'use,get,remove/baggie')
							(Said 'get/hair,dirt')
						)
						(if (localproc_000c) (Print 28 12))
					)
					((Said 'look/blood') (Print 28 13))
					((Said 'look,read/note,threat<death')
						(if (ego has: 6)
							((inventory at: 6) showSelf:)
						else
							(event claimed: 0)
						)
					)
					((Said 'check/sign,breathing') (if local1 (Print 28 14) else (Print 28 15)))
					((Said 'get,remove,hoist,move/body,person,dude')
						(cond 
							((and (> global182 0) (not local1)) (Bset 43) (curRoom newRoom: 27))
							(local1 (Print 28 16))
							(else (Print 28 17))
						)
					)
					((Said 'chat/gelepsi,cop,cop') (Print 28 18))
					((Said 'chat/coroner')
						(if (and (> 3 global182) (> global182 0))
							(Print 28 19)
						)
					)
					((Said 'chat/dude') (if local1 (Print 28 20) else (Print 28 21)))
					((Said 'look/face')
						(Print 28 22)
						(Print 28 23)
						(SolvePuzzle 1 141)
						(Print 28 24)
					)
					((Said 'look/body,person,dude') (Print 28 25))
					((Said 'look/injury,hole,head,burn') (Print 28 26))
					((Said 'look/cloth') (Print 28 27))
					((Said 'frisk,look/pocket,cloth') (Print 28 28))
					(
						(or
							(Said 'frisk/body,person,dude')
							(Said 'look,frisk/hand')
						)
						(if (not local1)
							(if (== ((inventory at: 24) owner?) 28)
								(Print 28 29)
							else
								(Print 28 30)
								(Print 28 31)
							)
						else
							(Print 28 32)
						)
					)
					((Said 'deposit/corner') (if (ego has: 24) (Print 28 33) else (Print 28 34)))
					((Said 'get,remove/corner')
						(if (and (not local1) (InRoom 24))
							(ego get: 24)
							(SolvePuzzle 2)
							(Print 28 35)
							(if (== global182 0) (= local0 25))
						else
							(Print 28 36)
						)
					)
					((Said 'get/note,newspaper')
						(if (and local1 (InRoom 6))
							(self changeState: 1)
						else
							(Print 28 37)
						)
					)
					(
						(or
							(Said 'exit[/trunk,lid]')
							(Said 'get<out')
							(Said 'walk')
						)
						(Bset 153)
						(self changeState: 3)
					)
					((Said 'close[/trunk,lid]') (Print 28 38) (Bset 153) (self changeState: 3))
				)
			)
		)
	)
)

;;; Sierra Script 1.0 - (do not remove this comment)
(script# 7)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm7 0
)

(local
	local0
	theFile
	bainsFile
	bottsFile
	loofinFile
	martinezFile
	southFile
	taselliFile
	westFile
	newView_3
	newView
	bainsMugShot
	local12
	local13
	local14
)
(procedure (localproc_0114)
	(= local12 1)
	(curRoom drawPic: 90 3)
	(switch theFile
		(bainsFile
			(mugShot loop: 2 cel: 0 y: 60 init:)
			(bainsMugShot posn: 31 28)
			(if (InRoom 23) (newView posn: 57 66))
			(RedrawCast)
			(Display 7 0 dsCOORD 120 15 dsWIDTH 200 dsFONT 0)
			(Display 7 1 dsCOORD 20 75 dsWIDTH 300)
		)
		(bottsFile
			(mugShot loop: 1 cel: 0 y: 60 init:)
			(RedrawCast)
			(Display 7 2 dsCOORD 120 10 dsWIDTH 200 dsFONT 0)
			(Display 7 3 dsCOORD 20 65 dsWIDTH 300)
		)
		(loofinFile
			(mugShot loop: 1 cel: 1 y: 160 init:)
			(RedrawCast)
			(Display 7 4 dsCOORD 20 10 dsWIDTH 300 dsFONT 0)
			(Display 7 5 dsCOORD 120 90 dsWIDTH 180)
		)
		(martinezFile
			(mugShot loop: 0 cel: 0 y: 160 init:)
			(RedrawCast)
			(Display 7 6 dsCOORD 20 10 dsWIDTH 300 dsFONT 0)
			(Display 7 7 dsCOORD 120 100 dsWIDTH 180)
		)
		(southFile
			(mugShot loop: 0 cel: 1 y: 160 init:)
			(RedrawCast)
			(Display 7 8 dsCOORD 20 10 dsWIDTH 300 dsFONT 0)
			(Display 7 9 dsCOORD 120 100 dsWIDTH 180)
		)
		(taselliFile
			(mugShot loop: 2 cel: 0 y: 170 init:)
			(RedrawCast)
			(Display 7 10 dsCOORD 20 10 dsWIDTH 280 dsFONT 0)
			(Display 7 11 dsCOORD 120 110 dsWIDTH 180)
		)
		(westFile
			(mugShot loop: 0 cel: 2 y: 160 init:)
			(RedrawCast)
			(Display 7 12 dsCOORD 20 10 dsWIDTH 280 dsFONT 0)
			(Display 7 13 dsCOORD 120 100 dsWIDTH 180)
		)
	)
	(= local13 0)
)

(procedure (localproc_0366)
	(= local12 0)
	(= local13 1)
	(curRoom drawPic: 90 2)
	(switch theFile
		(bainsFile
			(mugShot posn: 60 -30)
			(bainsMugShot posn: 0 0)
			(if (InRoom 23) (newView posn: 0 0))
			(Animate (cast elements?) 0)
			(Display 7 14 dsCOORD 20 7 dsWIDTH 300)
		)
		(bottsFile
			(mugShot posn: 60 -30)
			(Animate (cast elements?) 0)
			(Display 7 15 dsCOORD 20 10 dsWIDTH 300)
		)
		(loofinFile
			(mugShot posn: 60 -30)
			(Animate (cast elements?) 0)
			(Display 7 16 dsCOORD 20 10 dsWIDTH 300)
		)
		(martinezFile
			(mugShot posn: 60 -30)
			(Animate (cast elements?) 0)
			(Display 7 17 dsCOORD 20 10 dsWIDTH 300)
		)
		(southFile
			(mugShot posn: 60 -30)
			(Animate (cast elements?) 0)
			(Display 7 18 dsCOORD 20 10 dsWIDTH 300)
		)
		(taselliFile
			(mugShot posn: 60 -30)
			(Animate (cast elements?) 0)
			(Display 7 19 dsCOORD 20 10 dsWIDTH 300)
		)
		(westFile
			(mugShot posn: 60 -30)
			(Animate (cast elements?) 0)
			(Display 7 20 dsCOORD 20 10 dsWIDTH 300)
		)
	)
)

(instance rm7 of Room
	(properties)
	
	(method (init)
		(super init:)
		(HandsOff)
		(User canInput: 1)
		((= newView (View new:))
			view: 204
			loop: 2
			cel: 0
			posn: 0 0
			setPri: 12
			init:
		)
		((= bainsMugShot (View new:))
			view: 204
			loop: 2
			cel: 3
			posn: 0 0
			setPri: 14
			init:
		)
		(self setScript: fileScript)
	)
	
	(method (dispose)
		(fileScript dispose:)
		(super dispose:)
	)
)

(instance mugShot of View
	(properties
		x 60
		view 204
	)
)

(instance fileScript of Script
	(properties)
	
	(method (init)
		(self changeState: 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 7 6)
				((= newView_3 (View new:))
					view: 60
					posn: 156 110
					loop: 2
					cel: 0
					setPri: 15
					stopUpd:
					init:
				)
				((= bainsFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 160 99
					loop: 0
					cel: 0
					setPri: 14
					init:
					stopUpd:
				)
				((= bottsFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 160 93
					loop: 0
					cel: 1
					setPri: 12
					init:
					stopUpd:
				)
				((= loofinFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 161 87
					loop: 0
					cel: 2
					setPri: 10
					init:
					stopUpd:
				)
				((= martinezFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 160 78
					loop: 0
					cel: 3
					setPri: 8
					init:
					stopUpd:
				)
				((= southFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 160 74
					loop: 0
					cel: 4
					setPri: 6
					init:
					stopUpd:
				)
				((= taselliFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 161 67
					loop: 0
					cel: 5
					setPri: 4
					init:
					stopUpd:
				)
				((= westFile (Actor new:))
					view: 60
					ignoreActors:
					posn: 161 61
					loop: 0
					cel: 6
					setPri: 2
					init:
					stopUpd:
				)
			)
			(1
				(User canInput: 0)
				(if (< howFast 30)
					(self cue:)
				else
					(theFile
						setMotion: MoveTo (theFile x?) (- (theFile y?) 20) self
					)
				)
			)
			(2
				(bainsFile hide:)
				(bottsFile hide:)
				(loofinFile hide:)
				(martinezFile hide:)
				(southFile hide:)
				(taselliFile hide:)
				(westFile hide:)
				(newView_3 hide:)
				(SetMenu 513 112 0)
				(User canInput: 1)
				(HandsOn)
				(self cue:)
			)
			(3
				(= local12 1)
				(= local13 0)
				(= local14 1)
				(localproc_0114)
			)
			(5
				(User canInput: 0)
				(mugShot dispose:)
				(if (== theFile bainsFile)
					(bainsMugShot posn: 0 0)
					(if (not (ego has: 23)) (newView posn: 0 0))
				)
				(= local14 0)
				(self cue:)
			)
			(6
				(curRoom drawPic: 7 6)
				(bainsFile show:)
				(bottsFile show:)
				(loofinFile show:)
				(martinezFile show:)
				(southFile show:)
				(taselliFile show:)
				(westFile show:)
				(newView_3 show:)
				(SetMenu 513 112 1)
				(if (< howFast 30)
					(self cue:)
				else
					(theFile
						setMotion: MoveTo (theFile x?) (+ (theFile y?) 20) self
					)
				)
			)
			(7 (User canInput: 1))
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evKEYBOARD
				(if
					(or
						(== (event message?) KEY_F6)
						(== (event message?) KEY_F8)
						(== (event message?) KEY_F10)
					)
					(event claimed: 1)
					(CantDo)
				)
			)
			(evSAID
				(cond 
					(local14
						(cond 
							((Said 'get/mugshot,painting,shot[<mug]')
								(if (== theFile bainsFile)
									(if (InRoom 23)
										(newView dispose:)
										(ego get: 23)
										(SolvePuzzle 1)
									else
										(Print 7 21)
									)
								else
									(Print 7 22)
								)
							)
							((Said 'look/painting,mugshot,shot[<mug]') (if local12 (Print 7 23) else (Print 7 24)))
							(
								(or
									(Said '[turn,go,look]/2[<page]')
									(Said '[turn,go,look]/page<next,second')
									(Said 'read,look,see,turn/page[<next,second]')
								)
								(if local12
									(localproc_0366)
								else
									(Print 7 25 #at -1 140)
								)
							)
							(
								(or
									(Said '[read,turn,go,look]/1,1[<page]')
									(Said '[read,turn,go,look]/page<first,preceding')
									(Said 'turn,go<back[/1,1<page]')
									(Said 'turn,go<back[/page<first,preceding]')
									(Said '/back')
								)
								(if local13
									(localproc_0114)
								else
									(Print 7 26 #at -1 140)
								)
							)
							(
								(or
									(Said '[turn,go,look]/(page<next,third),(3<page)')
									(Said 'read,look,see,turn/(page[<third]),(3<page)')
								)
								(Print 7 27 #at -1 140)
							)
							(
								(Said
									'[read,see,look,get,open,pull]/file,bains,loofin,botts,martinez,julia,jason,lonny'
								)
								(Print 7 28 #at -1 140)
							)
							(
							(Said 'exit,close,exit,close/(drawer,cabinet)[<file]') (Print 7 29 #at -1 140))
							(
								(or
									(Said 'close,replace[/file]')
									(Said 'deposit[<back,away]/file')
								)
								(Print 7 30)
								(fileScript changeState: 5)
							)
						)
					)
					(
						(or
							(Said 'look[<in]/drawer,cabinet')
							(Said 'look<around')
						)
						(Print 7 31)
					)
					(
						(or
							(Said '[read,see,look,get,open,pull]/file<bains[<bains]')
							(Said '[read,see,look,get,open,pull]/bains[<bains]')
						)
						(= theFile bainsFile)
						(fileScript changeState: 1)
					)
					(
						(or
							(Said '[read,see,look,get,open,pull]/file<botts[<botts]')
							(Said '[read,see,look,get,open,pull]/botts[<botts]')
						)
						(= theFile bottsFile)
						(fileScript changeState: 1)
					)
					(
						(or
							(Said
								'[read,see,look,get,open,pull]/file<loofin[<loofin]'
							)
							(Said '[read,see,look,get,open,pull]/loofin[<loofin]')
						)
						(= theFile loofinFile)
						(fileScript changeState: 1)
					)
					(
						(or
							(Said
								'[read,see,look,get,open,pull]/file<martinez[<martinez]'
							)
							(Said '[read,see,look,get,open,pull]/martinez[<martinez]')
						)
						(= theFile martinezFile)
						(fileScript changeState: 1)
					)
					(
						(or
							(Said '[read,see,look,get,open,pull]/file<julia[<julia]')
							(Said '[read,see,look,get,open,pull]/julia[<julia]')
						)
						(= theFile southFile)
						(fileScript changeState: 1)
					)
					(
						(or
							(Said '[read,see,look,get,open,pull]/file<jason[<jason]')
							(Said '[read,see,look,get,open,pull]/jason[<jason]')
						)
						(= theFile taselliFile)
						(fileScript changeState: 1)
					)
					(
						(or
							(Said '[read,see,look,get,open,pull]/file<lonny[<lonny]')
							(Said '[read,see,look,get,open,pull]/lonny[<lonny]')
						)
						(= theFile westFile)
						(fileScript changeState: 1)
					)
					((Said 'look/painting,mugshot,shot[<mug]') (Print 7 32))
					((Said 'read,see,look,get,open,pull/file') (Print 7 33))
					((Said 'exit,close[/drawer,cabinet,file]') (cast eachElementDo: #dispose) (curRoom newRoom: 4))
				)
			)
		)
	)
)

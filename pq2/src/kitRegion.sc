;;; Sierra Script 1.0 - (do not remove this comment)
(script# regFieldKit)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Actor)

(public
	kitRegion 0
)

(local
	local0
	dontHaveFieldKit
)
(instance fKit of View
	(properties
		y 60
		x 81
		view 110
		priority 13
		signal $4111
	)
)

(instance dView of View
	(properties)
)

(instance kitRegion of Locale
	(properties)
	
	(method (init)
		(super init:)
		(= theFieldKit fKit)
		(= global117
			(= global118
				(= global119
					(= global120
						(= global121
							(= global122
								(= global123
									(= global124
										(= gDView dView)
									)
								)
							)
						)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(fKit dispose:)
		(= fieldKitOpen FALSE)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(= dontHaveFieldKit (not (ego has: iFieldKit)))
		(cond 
			((Said 'look,open,use/briefcase')
				(if dontHaveFieldKit
					(Print 153 0)
				else
					(if (!= fieldKitOpen TRUE)
						(fKit setPri: 13 ignoreActors: stopUpd: init:)
						(= fieldKitOpen TRUE)
					)
					(Print 153 1
						#at 184 1
						#draw
						#mode teJustCenter
						#width 110
					)
				)
			)
			((Said 'close/briefcase')
				(cond 
					(dontHaveFieldKit
						(Print 153 0)
					)
					((== fieldKitOpen FALSE)
						(Print 153 2)
					)
					(else
						(fKit dispose:)
						(= fieldKitOpen FALSE)
					)
				)
			)
			((Said 'look>')
				(cond 
					(
						(and
							(not (ego has: iFieldKit))
							(Said '/baggie,camera,plaster,(vial<glass),dropper,powder,brush,tape')
						)
						(Print 153 3)
					)
					((Said '/baggie')
						(Print 153 4)
					)
					((Said '/camera')
						(Print 153 5)
					)
					((Said '/plaster')
						(Print 153 6)
					)
					((Said '/vial/[anyword]')
						(event claimed: FALSE)
					)
					((or (Said '/vial') (Said '/glass<vial'))
						(Print 153 7)
					)
					((Said '/dropper')
						(Print 153 8)
					)
					((Said '/powder')
						(Print 153 9)
					)
					((Said '/brush')
						(Print 153 10)
					)
					((Said '/tape')
						(Print 153 11)
					)
				)
			)
			(
				(or
					(Said 'use,remove/powder,brush')
					(Said 'deposit,apply,use/powder,dust')
					(Said 'dust,powder[/anyword]')
					(Said 'get,remove,hoist/fingerprint,print[<finger]')
				)
				(if dontHaveFieldKit
					(Print 153 12)
				else
					(Print 153 13 #draw)
				)
			)
			((or (Said 'use,remove/camera') (Said 'get/painting'))
				(if dontHaveFieldKit
					(Print 153 12)
				else
					(Print 153 14)
				)
			)
			((Said 'use,remove/tape')
				(Print 153 15)
			)
			(
				(or
					(Said 'use,remove/dropper,vial,baggie')
					(Said 'get/sample')
				)
				(Print 153 16)
			)
			((Said 'make,use/plaster,cast,footprint,(print<feet)')
				(Print 153 17)
			)
		)
	)
)
